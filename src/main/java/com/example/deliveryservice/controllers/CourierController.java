package com.example.deliveryservice.controllers;

import com.example.deliveryservice.entities.AcceptedOrder;
import com.example.deliveryservice.entities.Order;
import com.example.deliveryservice.security.ClientDetails;
import com.example.deliveryservice.security.CourierDetails;
import com.example.deliveryservice.services.AcceptedOrderService;
import com.example.deliveryservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courier")
@PreAuthorize("hasAuthority('ROLE_COURIER')")
public class CourierController {
    OrderService orderService;
    AcceptedOrderService acceptedOrderService;
    @Autowired
    public CourierController(OrderService orderService, AcceptedOrderService acceptedOrderService){
        this.orderService = orderService;
        this.acceptedOrderService = acceptedOrderService;
    }
    @GetMapping("/order")
    public String orders(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CourierDetails courier = (CourierDetails)authentication.getPrincipal();
//      List<Order> ordersList = orderService.findByStatusAndId("accepted", courier.getId());
        List<AcceptedOrder> acceptedOrdersList = acceptedOrderService.findByCourierId(courier.getId());
        List<Order> ordersList = new ArrayList<>();
        for(AcceptedOrder acceptedOrder : acceptedOrdersList){
            ordersList.add(acceptedOrder.getOrder());
        }
        model.addAttribute("orders", ordersList);
        return "orderPage";
    }
    @GetMapping("/order/all")
    public String allOrders(Model model){
        List<Order> ordersList = orderService.findByStatus("available");
        model.addAttribute("orders", ordersList);
        return "orderPage";
    }
    @GetMapping("/order/{id}")
    public String order(Model model, @PathVariable Integer id){
        Order order = orderService.findById(id);
        model.addAttribute("orders", order);
        return "orderPage";
    }
    @GetMapping("")
    public String client(){
        return "courierPage";
    }
    @GetMapping("/order/{id}/accept")
    public String acceptOrder(@PathVariable Integer id){
        Order order = orderService.findByStatusAndId("available", id);
        System.out.println(order.getStatus());
        order.setStatus("accepted");
        System.out.println(order.getStatus());
        orderService.save(order);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CourierDetails courier = (CourierDetails)authentication.getPrincipal();
        acceptedOrderService.acceptOrder(order.getId(), courier.getId());
        return "orderPage";
    }
    @GetMapping("/order/{id}/finish")
    public String finishOrder(@PathVariable Integer id){
        Order order = orderService.findByStatusAndId("accepted", id);
        order.setStatus("finished");
        orderService.save(order);
        return "orderPage";
    }
}
