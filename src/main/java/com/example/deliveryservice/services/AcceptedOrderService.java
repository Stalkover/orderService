package com.example.deliveryservice.services;

import com.example.deliveryservice.dto.AcceptedOrderDto;
import com.example.deliveryservice.entities.AcceptedOrder;
import com.example.deliveryservice.entities.Courier;
import com.example.deliveryservice.entities.Order;
import com.example.deliveryservice.repositories.AcceptedOrderRepository;
import com.example.deliveryservice.repositories.CourierRepository;
import com.example.deliveryservice.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcceptedOrderService {
    private final AcceptedOrderRepository acceptedOrderRepository;
    private final OrderRepository orderRepository;
    private final CourierRepository courierRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public AcceptedOrderService(AcceptedOrderRepository acceptedOrderRepository, OrderRepository orderRepository, CourierRepository courierRepository){
        this.acceptedOrderRepository = acceptedOrderRepository;
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
    }

    @Transactional
    public void save(AcceptedOrder acceptedOrder){
        acceptedOrderRepository.save(acceptedOrder);
    }

    @Transactional
    public void save(AcceptedOrderDto acceptedOrderDto){
        AcceptedOrder acceptedOrder = mapper.map(acceptedOrderDto, AcceptedOrder.class);
        acceptedOrderRepository.save(acceptedOrder);
    }

    @Transactional
    public void acceptOrder(Integer orderId, Integer courierId){
        AcceptedOrder acceptedOrder = new AcceptedOrder();
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Courier courier = courierRepository.findById(courierId).orElseThrow(() -> new RuntimeException("Courier not found"));
        acceptedOrder.setOrder(order);
        acceptedOrder.setCourier(courier);
        acceptedOrderRepository.save(acceptedOrder);
    }

    public List<AcceptedOrder> findByCourierId(Integer courierId){
        return acceptedOrderRepository.findByCourierId(courierId);
    }

}
