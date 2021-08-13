package com.fyp.fyp.controller.page;

import com.fyp.fyp.dao.QuestionDao;
import com.fyp.fyp.entity.CandidateResponse;
import com.fyp.fyp.entity.Question;
import com.fyp.fyp.entity.CandidateResponseKey;
import com.fyp.fyp.services.CandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

//Handles all of the functionality for the Candidate page
@Controller
public class CandidatePageController {
    @Autowired
    QuestionDao questionDao;

    @Autowired
    CandidateServiceImpl candidateService;

    //Returns to main mapping page
    @GetMapping("/candidate")
    public String getCandidateMainPage(Principal principal,
                                       Model model) {
        List<Question> qs = candidateService.getUnansweredQuestions(principal.getName());
        model.addAttribute("unansweredQuestions", qs);
        return "candidate/candidate";
    }

    //displays the questions that a candidate has responded to
    @PostMapping("/candidateResponse")
    public RedirectView questionResponse(@RequestParam("questionID") Long questionID,
                                         @RequestParam("responseNum") Integer responseNum,
                                         Principal principal,
                                         Model model) {
        CandidateResponse candidateResponse = new CandidateResponse();
        CandidateResponseKey key = new CandidateResponseKey();
        //inject username here as more secure
        key.setUsername(principal.getName());
        key.setQuestionID(questionID);

        candidateResponse.setId(key);
        candidateResponse.setResponseNum(responseNum);
        candidateResponse.setQuestion(questionDao.getById(questionID));

        candidateService.saveQuestionResponse(candidateResponse);

        return new RedirectView("/candidate");
    }

    @GetMapping("/answeredQuestions")
    public String getAnsweredQuestionsPage(Principal principal,
                                           Model model) {
        List<CandidateResponse> qs = candidateService.getAnsweredQuestions(principal.getName());
        model.addAttribute("answeredQuestions", qs);
        return "candidate/candidateAnsweredQuestions";
    }

    @PostMapping("/editQuestionResponse")
    public RedirectView editQuestionResponse(@RequestParam("questionID") Long questionID,
                                             @RequestParam("responseNum") Integer responseNum,
                                             Principal principal,
                                             Model model) {
        CandidateResponse candidateResponse = new CandidateResponse();
        CandidateResponseKey key = new CandidateResponseKey();
        //inject username here as more secure
        key.setUsername(principal.getName());
        key.setQuestionID(questionID);

        candidateResponse.setId(key);
        candidateResponse.setResponseNum(responseNum);
        candidateResponse.setQuestion(questionDao.getById(questionID));

        candidateService.saveQuestionResponse(candidateResponse);

        return new RedirectView("/answeredQuestions");
    }

    @GetMapping("/jobsSelectedFor")
    public String getJobsSelectedForPage(Principal principal,
                                         Model model) {

        return "candidate/candidateJobsMatch";
    }
}
