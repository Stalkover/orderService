package com.example.deliveryservice.dto;

import lombok.Data;

@Data
public class ClientDto {
    //private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}
