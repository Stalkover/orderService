package com.example.deliveryservice.utils;

import com.example.deliveryservice.dto.ClientDto;
import com.example.deliveryservice.entities.Client;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public ClientDto mapToClientDto(Client client){
        ClientDto dto = new ClientDto();
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setEmail(client.getEmail());
        dto.setPhoneNumber(client.getPhoneNumber());
        dto.setPassword(client.getPassword());
        return dto;
    }
    public Client mapToClientEntity(ClientDto dto){
        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setPhoneNumber(dto.getPhoneNumber());
        client.setPassword(dto.getPassword());
        return client;
    }

}
