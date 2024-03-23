package com.example.orderService.repositories;

import com.example.orderService.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neil Alishev
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
