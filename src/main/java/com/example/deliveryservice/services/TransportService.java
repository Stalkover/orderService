package com.example.deliveryservice.services;

import com.example.deliveryservice.entities.Transport;
import com.example.deliveryservice.repositories.TransportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportService {
    private final TransportRepository transportRepository;

    @Autowired
    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Transactional
    public void save(Transport transport){
        transportRepository.save(transport);
    }
}
