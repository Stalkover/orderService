package com.example.deliveryservice.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
    private String transport;
}
