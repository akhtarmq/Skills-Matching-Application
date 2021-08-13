package com.fyp.fyp.controller.page;

import com.fyp.fyp.entity.JobSpec;
import com.fyp.fyp.entity.Question;
import com.fyp.fyp.entity.QuestionType;
import com.fyp.fyp.entity.User;
import com.fyp.fyp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

//Handles all functionality for the manager page
@Controller
public class ManagerPageController {

    @Autowired
    UserService userService;

    @Autowired
    JobSpecService jobSpecService;

    //Display the Main page for the hiring manager that lists all of the current job specs.
    @GetMapping("/hm")
    public String getHmMainPage(Principal principal, Model model) {
        return getHmMainPage(principal.getName(), model);
    }

    public String getHmMainPage(String username, Model model) {
        model.addAttribute("jobSpecs", jobSpecService.listJobSpecs(username));
        return "hm/hiringManager";
    }

    //Displays the page for hiring manager to create a new job spec
    @GetMapping("/newJobSpec")
    public String getNewJobSpecPage(Model model) {
        return "hm/hiringManagerJobSpec";
    }

    //saves a newly created job spec
    @PostMapping("/newJobSpec")
    public RedirectView newJobSpec(Principal principal,
                                   @ModelAttribute("jobSpec") JobSpec jobSpec,
                                   Model model) {
        //TODO - make generic method for throwing this exception and add string
        User user = userService.findById(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(""));
        //TODO validate duplicates
        jobSpec.setCreatedBy(user); //TODO fix this
        jobSpecService.saveNewJobSpec(jobSpec);
        return new RedirectView("/hm");
    }

    //Displays the Job Specs
    @GetMapping("/jobSpec/{jobSpecID}")
    public String getActiveJobsPage(@PathVariable Long jobSpecID, Model model) {
        //TODO throw exception if not found
        JobSpec jobSpec = jobSpecService.findById(jobSpecID).get();
        List<QuestionType> questionTypes = jobSpecService.getAllQuestionTypes();
        model.addAttribute("jobSpec", jobSpec);
        model.addAttribute("questionTypes", questionTypes);
        return "hm/viewJobSpecSelected";
    }

    //Saves a new Questions that has been created
    @PostMapping("/jobSpec/{jobSpecID}/saveNewQuestion")
    public RedirectView saveNewQuestion(@PathVariable Long jobSpecID,
                                        @ModelAttribute("question") Question question,
                                        Model model) {
        jobSpecService.saveNewQuestion(jobSpecID, question);
        return new RedirectView("/jobSpec/" + jobSpecID);
    }

    //Removes a questions
    @GetMapping("/jobSpec/{jobSpecID}/removeQuestion/{questionID}")
    public RedirectView removeQuestion(@PathVariable Long jobSpecID,
                                       @PathVariable Long questionID,
                                       Principal principal,
                                       Model model) {
        jobSpecService.removeQuestion(jobSpecID, questionID);
        //TODO redirect URL
        return new RedirectView("/jobSpec/" + jobSpecID);
    }

    //Displays a list of the matched candidates
    @GetMapping("/matchedCandidates/{jobSpecID}")
    public String getMatchedCandidates(@PathVariable Long jobSpecID, Model model) {
        //TODO throw exception if not found
        model.addAttribute("jobSpecID", jobSpecID);
        model.addAttribute("users", userService.listMatchedCandidates(jobSpecID));
        return "hm/hiringManagerViewMatchedCandidates";
    }

    //Allows a candidate to be selected.
    @PostMapping("/matchedCandidates/selectCandidate")
    public RedirectView selectCandidate(@RequestParam("username") String username,
                                        @RequestParam("jobSpecID") Long jobSpecID,
                                        Principal principal,
                                        Model model) {
        jobSpecService.selectCandidate(username, jobSpecID);
        return new RedirectView("/matchedCandidates/" + jobSpecID);
    }

    @PostMapping(value = "/addQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public RedirectView addQuestion(@RequestParam("jobSpecId") Long jobSpecId,
                              @RequestParam("questionId") Long questionId) {
        jobSpecService.addQuestion(jobSpecId, questionId);
        return new RedirectView("/jobSpec/" + jobSpecId);
    }
}
