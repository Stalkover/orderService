package com.example.deliveryservice.services;

import com.example.deliveryservice.dto.CourierDto;
import com.example.deliveryservice.entities.Client;
import com.example.deliveryservice.entities.Courier;
import com.example.deliveryservice.entities.Transport;
import com.example.deliveryservice.repositories.ClientRepository;
import com.example.deliveryservice.repositories.CourierRepository;
import com.example.deliveryservice.repositories.TransportRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CourierService {
    private final ModelMapper mapper = new ModelMapper();
    private final CourierRepository courierRepository;
    private final PasswordEncoder passwordEncoder;
    private final TransportRepository transportRepository;

    @Autowired
    public CourierService(CourierRepository courierRepository,
                          @Lazy PasswordEncoder passwordEncoder,
                            TransportRepository transportRepository){
        this.courierRepository=courierRepository;
        this.passwordEncoder=passwordEncoder;
        this.transportRepository=transportRepository;
    }

    @Transactional
    public void save(Courier courier){courierRepository.save(courier);}
    public void save(CourierDto courierDto){
        courierDto.setPassword(passwordEncoder.encode(courierDto.getPassword()));
        Courier courier = mapper.map(courierDto, Courier.class);
        Transport transport = transportRepository.findByTransportType(courierDto.getTransport());
        courier.setTransport(transport);
        courierRepository.save(courier);
    }

    @Transactional
    public CourierDto findByEmail(String email){
        Courier courier = courierRepository.findByEmail(email);
        if(courier != null){
            return mapper.map(courier, CourierDto.class);
        }else{
            return null;
        }
    }
}
