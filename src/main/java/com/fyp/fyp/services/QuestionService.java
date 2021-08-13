package com.fyp.fyp.services;

import com.fyp.fyp.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions();
    List<Question> getQuestionsLike(String questionTextLike);
    List<Question> getQuestionsLikeForJobSpec(Long jobSpecId, String questionTextLike);
}
