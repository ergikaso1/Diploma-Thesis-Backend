package com.diploma.ergi.service;

import com.diploma.ergi.entity.Feedback;
import com.diploma.ergi.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class FeedbackService {

    private FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository){
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback saveFeedback(Feedback feedback){
        return feedbackRepository.save(feedback);
    }

    public BigDecimal getAverageRating() {
        return feedbackRepository.findAverageRating();
    }

    public List<Feedback> getAll(){
        return feedbackRepository.findAll();
    }
}
