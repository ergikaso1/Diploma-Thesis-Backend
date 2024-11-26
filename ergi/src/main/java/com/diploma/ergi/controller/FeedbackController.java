package com.diploma.ergi.controller;


import com.diploma.ergi.entity.Feedback;
import com.diploma.ergi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService){
        this.feedbackService = feedbackService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFeedback(@RequestBody Feedback feedback){
        feedbackService.saveFeedback(feedback);
        return ResponseEntity.ok("Feedback data added!");
    }

    @GetMapping("/average-rating")
    public BigDecimal getAverageRating() {
        return feedbackService.getAverageRating();
    }

    @GetMapping("/all")
    public List<Feedback> getAll(){
        return feedbackService.getAll();
    }

}
