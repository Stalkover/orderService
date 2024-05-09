package com.example.deliveryservice.repositories;

import com.example.deliveryservice.entities.Courier;
import org.springframework.data.repository.CrudRepository;

public interface CourierRepository extends CrudRepository<Courier, Integer> {
    Courier findByEmail(String email);
}
