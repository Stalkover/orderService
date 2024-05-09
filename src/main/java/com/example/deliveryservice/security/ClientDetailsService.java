package com.example.deliveryservice.security;

import com.example.deliveryservice.dto.ClientDto;
import com.example.deliveryservice.entities.Client;
import com.example.deliveryservice.repositories.ClientRepository;
import com.example.deliveryservice.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientDetailsService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            return null;
        }
        return new ClientDetails(client);
    }
}
