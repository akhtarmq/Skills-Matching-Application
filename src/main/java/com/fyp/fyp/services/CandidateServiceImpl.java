package com.fyp.fyp.services;

import com.fyp.fyp.dao.CandidateResponseDao;
import com.fyp.fyp.dao.QuestionDao;
import com.fyp.fyp.dao.QuestionOptionsDao;
import com.fyp.fyp.dao.SelectedCandidateDao;
import com.fyp.fyp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Contains all the functions related to managing Candidates
 */
@Service
public class CandidateServiceImpl implements CandidateService {

    /**
     * Used for QuestionResponse entity persistence
     */
    @Autowired
    private CandidateResponseDao questionResponseDao;

    /**
     * used for Question entity persistance
     */
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuestionOptionsDao optionsDao;

    /**
     * used for SelectedCandidate entity persistance
     */
    @Autowired
    private SelectedCandidateDao selectedCandidateDao;

//    public List<SelectedCandidate> getCandidates(int jobSpecID) {
//       return selectedCandidateDao.findByJobSpecID(jobSpecID);
//    }

    /**
     * Gets all available candidates
     * @return List of available candidates
     */
    public List<SelectedCandidate> getUnapprovedCandidates() {
        //TODO fix perf issue here
        return
        selectedCandidateDao.findAll()
                            .stream()
                            .filter(c -> c.getApproved() == null)
                            .toList();
    }

    public List<SelectedCandidate> getProcessedCandidates() {
        //TODO fix perf issue here
        return
            selectedCandidateDao.findAll()
                    .stream()
                    .filter(c -> c.getApproved() != null)
                    .toList();
    }

    //TODO we should only get questions that have an active job spec
    //TODO add javadoc
    public List<Question> getUnansweredQuestions(String username) {
        List<Question> questions = questionDao.getUnansweredQuestions(username);

        for(Question question : questions) {
            Set<QuestionOptions> options = optionsDao.findAllByIdQuestionID(question.getQuestionID());
            question.setOptions(options);
        }

        return questions;
    }

    /**
     * lists all of the questions that have not been answered
     * @param username
     * @return list of questions
     */
    public List<CandidateResponse> getAnsweredQuestions(String username) {
        List<CandidateResponse> responses = questionResponseDao.findAllByIdUsername(username);
        return responses;
    }

    /**
     * saves question responses
     * @param response
     * @return a question response
     */
    public CandidateResponse saveQuestionResponse(CandidateResponse response) {
        return questionResponseDao.save(response);
    }

    /**
     * approves a candidate
     * @param username
     * @param jobSpecId
     * @return saves the response of hr
     */
    //Approves a candidate
    public void approveUser(String username, Long jobSpecId) {
        SelectedCandidateId id = new SelectedCandidateId();
        id.setUsername(username);
        id.setJobSpecID(jobSpecId);

        //TODO catch if could not find anything
        SelectedCandidate can = selectedCandidateDao.findById(id).get();
        can.setApproved(true);

        selectedCandidateDao.save(can);
    }

    /**
     * declines a candidate
     * @param username
     * @param jobSpecId
     * @return saves the response of hr
     */
    //Declines a candidate
    public void declineUser(String username, Long jobSpecId) {
        SelectedCandidateId id = new SelectedCandidateId();
        id.setUsername(username);
        id.setJobSpecID(jobSpecId);

        //TODO catch if could not find anything
        SelectedCandidate can = selectedCandidateDao.findById(id).get();
        can.setApproved(false);

        selectedCandidateDao.save(can);
    }
}
