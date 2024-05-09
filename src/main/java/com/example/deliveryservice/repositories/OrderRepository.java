package com.example.deliveryservice.repositories;

import com.example.deliveryservice.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findByClientId(Integer clientId);
    Order findByClientIdAndId(Integer clientId, Integer orderId);
    List<Order> findByStatus(String status);
    Order findByStatusAndId(String status, Integer id);
//    List<Order> findByCourierId(Integer courierId);
//    List<Order> findByCourierIdAndId(Integer courierId, Integer id);
}
