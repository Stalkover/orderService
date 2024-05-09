package com.example.deliveryservice.repositories;

import com.example.deliveryservice.entities.Feedback;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
}
