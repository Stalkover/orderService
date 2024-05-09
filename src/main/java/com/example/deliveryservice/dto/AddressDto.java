package com.example.deliveryservice.dto;

import lombok.Data;

@Data
public class AddressDto {
    //private Integer id;
    private String address;
    private Double latitude;
    private Double longitude;
}
