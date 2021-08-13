package com.fyp.fyp.services;

import com.fyp.fyp.dao.QuestionDao;
import com.fyp.fyp.entity.JobSpecQuestions;
import com.fyp.fyp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    JobSpecService jobSpecService;

    @Autowired
    QuestionDao questionDao;

    public List<Question> getQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsLike(String questionTextLike) {
        List<Question> qs = questionDao.findByQuestionTextContainingIgnoreCase(questionTextLike);
        qs.stream().forEach(q -> q.getOptions().stream().forEach(o -> o.setQuestion(null)));
        return qs;
    }

    public List<Question> getQuestionsLikeForJobSpec(Long jobSpecId, String questionTextLike) {
        List<Long> jobQs = jobSpecService.findById(jobSpecId)
                                         .get()
                                         .getQuestions()
                                         .stream()
                                         .map(j -> j.getId().getQuestionID())
                                         .toList();
        List<Question> similarQs = getQuestionsLike(questionTextLike);
        similarQs.removeIf(q -> jobQs.contains(q.getQuestionID()));
        return similarQs;
    }
}
