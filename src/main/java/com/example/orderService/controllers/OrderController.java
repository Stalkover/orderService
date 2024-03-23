package com.example.orderService.controllers;

import com.example.orderService.dto.Order;

import com.example.orderService.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    @PostMapping("/order")
    public ResponseEntity<HttpStatus> createOrder(@RequestBody Order order){
        orderService.save(order);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
