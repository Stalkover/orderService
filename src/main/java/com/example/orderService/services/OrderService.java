package com.example.orderService.services;

import com.example.orderService.dto.Order;

import com.example.orderService.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @Transactional
    public void save(Order order){
        orderRepository.save(order);
    }
}
