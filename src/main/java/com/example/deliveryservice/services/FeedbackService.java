package com.example.deliveryservice.services;

import com.example.deliveryservice.entities.Courier;
import com.example.deliveryservice.entities.Feedback;
import com.example.deliveryservice.repositories.CourierRepository;
import com.example.deliveryservice.repositories.FeedbackRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public void save(Feedback feedback){feedbackRepository.save(feedback);}
}
