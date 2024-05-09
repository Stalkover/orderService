package com.example.deliveryservice.repositories;

import com.example.deliveryservice.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findByEmail(String email);
}
