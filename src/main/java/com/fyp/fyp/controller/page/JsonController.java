package com.fyp.fyp.controller.page;

import com.fyp.fyp.entity.Question;
import com.fyp.fyp.services.JobSpecService;
import com.fyp.fyp.services.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class JsonController {

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    JobSpecService jobSpecService;

    @GetMapping(value = "/questions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Question> getQuestions(@RequestParam("qText") String qText) {
        return questionService.getQuestions();
    }

    @GetMapping(value = "/questionsLike", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Question> getQuestionsLike(@RequestParam("qText") String qText,
                                           @RequestParam("jobSpecId") Long jobSpecId) {
        return questionService.getQuestionsLikeForJobSpec(jobSpecId, qText);
    }

    @PostMapping(value="/jobSpec/{jobSpecID}/saveNewQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveNewQuestion(@PathVariable Long jobSpecID,
                                  @RequestBody Question question,
                                  Model model) {
        jobSpecService.saveNewQuestion(jobSpecID, question);
        return "{}";
    }

//    @PostMapping(value = "/addQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public void addQuestion(@RequestParam("jobSpecId") Long jobSpecId,
//                            @RequestParam("questionId") Long questionId) {
//        jobSpecService.addQuestion(jobSpecId, questionId);
//    }

//    @GetMapping(value = "/addQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public void addQuestion(@RequestParam("jobSpecId") Long jobSpecId,
//                            @RequestParam("questionId") Long questionId) {
//        jobSpecService.addQuestion(jobSpecId, questionId);
//    }
}
