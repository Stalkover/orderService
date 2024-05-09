package com.example.deliveryservice.dto;

import com.example.deliveryservice.entities.Address;
import com.example.deliveryservice.entities.Client;
import com.example.deliveryservice.entities.Transport;
import lombok.Data;

@Data
public class OrderDto {
    private String client;
    private String initialAddress;
    private String destinationAddress;
    private String description;
    private String status;
    private String transport;

//    private Double totalAmount;
//    private Double travelDistance;
//    private Long travelTime;
}
