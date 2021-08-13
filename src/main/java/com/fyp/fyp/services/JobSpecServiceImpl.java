package com.fyp.fyp.services;

import com.fyp.fyp.dao.*;
import com.fyp.fyp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobSpecServiceImpl implements JobSpecService {

    @PersistenceContext
    EntityManager entityManager;

    /**
     *  Used for Selected Candidate entity persistence
     */
    @Autowired
    SelectedCandidateDao candidateDao;

    /**
     * Used for Question entity persistence
     */
    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuestionOptionsDao optionsDao;

    /**
     * Used for Jobspec Questions entity persistence
     */
    @Autowired
    JobSpecQuestionsDao jobSpecQuestionsDao;

    /**
     * Used for Jobspec entity persistence
     */
    @Autowired
    JobSpecDao jobSpecDao;

    /**
     * list all of the job specs
     * @param username
     * @return list of job specs
     */
    //returns all of the job specs
    public List<JobSpec> listJobSpecs(String username) {
        //TODO perf - filter at DB level instead of getting all records
        return
        jobSpecDao.findAll()
                  .stream()
                  .filter(j -> j.getCreatedBy().getUsername().equals(username))
                  .toList();
    }

    /**
     * saves new jobs specs in the database
     * @param jobSpec
     * @return a jobspec
     */
    //saves a new job spec in the database.
    public JobSpec saveNewJobSpec(JobSpec jobSpec) {
        return jobSpecDao.save(jobSpec);
    }

    /**
     * Finds jobspec by its unique ID
     * @param jobSpecID
     * @return Jobspec with unique ID
     */
    //Allows jobSpec to be found by its Unique ID
    public Optional<JobSpec> findById(Long jobSpecID) {
        return jobSpecDao.findById(jobSpecID);
    }

    /**
     * Lists all of the different question types
     * @return list of the question types
     */
    //Displays a list of the different question types
    public List<QuestionType> getAllQuestionTypes() {
        return Arrays.stream(QuestionType.values()).toList();
    }

    /**
     * Saves a new question
     * @param jobSpecID
     * @param question
     * @return saved question
     */
    //Allows a new question to be saved
    @Transactional
    public JobSpecQuestions saveNewQuestion(Long jobSpecID, Question question) {
        Question savedQuestion = questionDao.save(question);
        JobSpec jobSpec = jobSpecDao.getById(jobSpecID);

        JobSpecQuestionsKey key = new JobSpecQuestionsKey(jobSpecID, savedQuestion.getQuestionID());
        JobSpecQuestions jobSpecQuestions =
                new JobSpecQuestions(new JobSpecQuestionsKey(), jobSpec, savedQuestion, 1, 2);
        entityManager.persist(jobSpecQuestions);

        Set<QuestionOptions> options = savedQuestion.getOptions();

        if(options != null && options.size() > 0) {
            for(int i = 0; i < options.size(); i++) {
                QuestionOptions option = options.stream().toList().get(i);
                QuestionOptionsKey id = new QuestionOptionsKey();
                id.setQuestionID(savedQuestion.getQuestionID());
                id.setSortSequence(i);
                option.setId(id);
                option.setQuestion(savedQuestion);
                optionsDao.save(option);
            }
        }

        return jobSpecQuestionsDao.getById(key);
    }

    @Transactional
    public JobSpecQuestions addQuestion(Long jobSpecID, Long questionId) {
        Question question = questionDao.getById(questionId);
        JobSpec jobSpec = jobSpecDao.getById(jobSpecID);

        JobSpecQuestionsKey key = new JobSpecQuestionsKey(jobSpecID, questionId);
        JobSpecQuestions jobSpecQuestions =
                new JobSpecQuestions(new JobSpecQuestionsKey(), jobSpec, question, 1, 2);
        entityManager.persist(jobSpecQuestions);
        return jobSpecQuestionsDao.getById(key);
    }

    /**
     * removes a question
     * @param jobSpecID
     * @param questionID
     * @return deletes a question
     */
    //Allows a Question to be removed
    public void removeQuestion(Long jobSpecID, Long questionID) {
        JobSpecQuestionsKey key = new JobSpecQuestionsKey(jobSpecID, questionID);
        jobSpecQuestionsDao.deleteById(key);
    }

    /**
     * Allows candidate to be selected for a job
     * @param userName
     * @param jobSpecID
     * @return returns a selected candidate
     */
    //Allows a candidate to be selected for a job
    //2 params - username and jobSpecId
    public void selectCandidate(String userName, Long jobSpecID) {
        SelectedCandidateId ID = new SelectedCandidateId();
        ID.setJobSpecID(jobSpecID);
        ID.setUsername(userName);

        SelectedCandidate candidate = new SelectedCandidate();
        candidate.setSelectedCandidateId(ID);

        candidateDao.save(candidate);
        //create new SelectedCandidateID  object then set the username and jobSpecId in it
        //create new SelectedCandidate and set selectedCandidateId to what we just created
        //use SelectedCAndidateDao to save new record SelectedCAndidate
    }
}
