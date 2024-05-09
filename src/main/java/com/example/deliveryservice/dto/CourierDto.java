package com.example.deliveryservice.dto;

import lombok.Data;

@Data
public class CourierDto {
    //private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String transport;
}
