package com.fyp.fyp.services;

import com.fyp.fyp.entity.JobSpec;
import com.fyp.fyp.entity.JobSpecQuestions;
import com.fyp.fyp.entity.Question;
import com.fyp.fyp.entity.QuestionType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface JobSpecService {
    List<JobSpec> listJobSpecs(String username);
    JobSpec saveNewJobSpec(JobSpec jobSpec);
    Optional<JobSpec> findById(Long jobSpecID);
    List<QuestionType> getAllQuestionTypes();
    JobSpecQuestions saveNewQuestion(Long jobSpecID, Question question);
    void removeQuestion(Long jobSpecID, Long questionID);
    void selectCandidate(String userName, Long jobSpecID);
    JobSpecQuestions addQuestion(Long jobSpecID, Long questionId);
}
