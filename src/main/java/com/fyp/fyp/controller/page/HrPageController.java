package com.fyp.fyp.controller.page;

import com.fyp.fyp.services.CandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//Handles all of the functionality for the HR page
@Controller
public class HrPageController {

    @Autowired
    CandidateServiceImpl candidateService;
//Returns all of the candidates that have been selected by the hiring manager
    @GetMapping("/hr")
    public String getHrMainPage(Model model) {
        model.addAttribute("selectedCandidates", candidateService.getUnapprovedCandidates());
        return "hr/hr";
    }

    @GetMapping("/completedRequests")
    public String getHrCompletedRequestsPage(Model model) {
        model.addAttribute("processedCandidates", candidateService.getProcessedCandidates());
        return "hr/hrCompletedRequests";
    }

    @RequestMapping(value = "/approveUser", method = RequestMethod.POST, params = "action=accept")
    public String approveUser(@RequestParam("username") String username,
                              @RequestParam("jobSpecID") Long jobSpecID,
                              Model model) {
        candidateService.approveUser(username, jobSpecID);
        return getHrMainPage(model);
    }

    @RequestMapping(value = "/approveUser", method = RequestMethod.POST, params = "action=decline")
    public String declineUser(@RequestParam("username") String username,
                              @RequestParam("jobSpecID") Long jobSpecID,
                              Model model){
        candidateService.declineUser(username, jobSpecID);
        return getHrMainPage(model);
    }

    @RequestMapping(value = "/changeApproveUser", method = RequestMethod.POST, params = "action=accept")
    public String changeToApproveUser(@RequestParam("username") String username,
                                      @RequestParam("jobSpecID") Long jobSpecID,
                                      Model model) {
        candidateService.approveUser(username, jobSpecID);
        return getHrCompletedRequestsPage(model);
    }

    @RequestMapping(value = "/changeApproveUser", method = RequestMethod.POST, params = "action=decline")
    public String changeToDeclineUser(@RequestParam("username") String username,
                                      @RequestParam("jobSpecID") Long jobSpecID,
                                      Model model){
        candidateService.declineUser(username, jobSpecID);
        return getHrCompletedRequestsPage(model);
    }
}
