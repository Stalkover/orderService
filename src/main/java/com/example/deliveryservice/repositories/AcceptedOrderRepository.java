package com.example.deliveryservice.repositories;

import com.example.deliveryservice.entities.AcceptedOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AcceptedOrderRepository extends CrudRepository<AcceptedOrder, Integer> {
    public List<AcceptedOrder> findByCourierId(Integer courierId);
}
