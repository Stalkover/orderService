package com.example.deliveryservice.dto;

import com.example.deliveryservice.entities.Courier;
import com.example.deliveryservice.entities.Order;
import lombok.Data;

@Data
public class AcceptedOrderDto {
    Order order;
    Courier courier;
}
