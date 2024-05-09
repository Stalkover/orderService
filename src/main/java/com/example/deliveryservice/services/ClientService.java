package com.example.deliveryservice.services;

import com.example.deliveryservice.dto.ClientDto;
import com.example.deliveryservice.entities.Client;
import com.example.deliveryservice.repositories.ClientRepository;
import com.example.deliveryservice.utils.MappingUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientRepository clientRepository, MappingUtils mappingUtils, @Lazy PasswordEncoder passwordEncoder){
        this.mapper=new ModelMapper();
        this.clientRepository=clientRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Transactional
    public void save(Client client){clientRepository.save(client);}

    @Transactional
    public void save(ClientDto clientDto){
        clientDto.setPassword(passwordEncoder.encode(clientDto.getPassword()));
        Client client = mapper.map(clientDto, Client.class);
        clientRepository.save(client);
    }

    @Transactional
    public ClientDto findByEmail(String email){
        Client client = clientRepository.findByEmail(email);
        if(client != null){
            return mapper.map(client, ClientDto.class);
        }else{
            return null;
        }

    }
}
