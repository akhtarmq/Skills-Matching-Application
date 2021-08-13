package com.fyp.fyp.services;

import com.fyp.fyp.entity.CandidateResponse;
import com.fyp.fyp.entity.Question;
import com.fyp.fyp.entity.SelectedCandidate;

import java.util.List;

public interface CandidateService {
    List<SelectedCandidate> getUnapprovedCandidates();
    List<SelectedCandidate> getProcessedCandidates();
    List<Question> getUnansweredQuestions(String username);
    CandidateResponse saveQuestionResponse(CandidateResponse response);
    void approveUser(String username, Long jobSpecId);
    void declineUser(String username, Long jobSpecId);

}
