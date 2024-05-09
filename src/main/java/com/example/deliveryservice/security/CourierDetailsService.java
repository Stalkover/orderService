package com.example.deliveryservice.security;

import com.example.deliveryservice.dto.CourierDto;
import com.example.deliveryservice.entities.Courier;
import com.example.deliveryservice.repositories.CourierRepository;
import com.example.deliveryservice.services.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CourierDetailsService implements UserDetailsService {

    private final CourierRepository courierRepository;

    public CourierDetailsService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Courier courier = courierRepository.findByEmail(email);
        if(courier == null){
            return null;
        }
        return new CourierDetails(courier);
    }
}
