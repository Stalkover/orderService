package com.example.deliveryservice.repositories;

import com.example.deliveryservice.entities.Transport;
import org.springframework.data.repository.CrudRepository;

public interface TransportRepository extends CrudRepository<Transport, Integer> {
    Transport findByTransportType(String transport);
}
