package com.example.deliveryservice.controllers;

import com.example.deliveryservice.entities.AcceptedOrder;
import com.example.deliveryservice.entities.Order;
import com.example.deliveryservice.security.ClientDetails;
import com.example.deliveryservice.security.CourierDetails;
import com.example.deliveryservice.services.AcceptedOrderService;
import com.example.deliveryservice.services.OrderService;
import com.example.deliveryservice.utils.Coordinate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courier")
@PreAuthorize("hasAuthority('ROLE_COURIER')")
@CrossOrigin
public class CourierController {
    private OrderService orderService;
    private AcceptedOrderService acceptedOrderService;
    private final RestTemplate restTemplate = new RestTemplate();
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
        return "courierOrderPage";
    }
    @GetMapping("/order/all")
    public String allOrders(Model model){
        List<Order> ordersList = orderService.findByStatus("available");
        model.addAttribute("orders", ordersList);
        return "courierOrderPage";
    }
    @GetMapping("/order/{id}")
    public String order(Model model, @PathVariable Integer id) throws JsonProcessingException {
        Order order = orderService.findById(id);
        model.addAttribute("orders", order);
        double lat1 = order.getInitialAddress().getLatitude();
        double lon1 = order.getInitialAddress().getLongitude();
        double lat2 = order.getDestinationAddress().getLatitude();
        double lon2 = order.getDestinationAddress().getLongitude();
        String url = "https://routing-app-dn5lmhhzmq-uc.a.run.app";
        String fullUrl = url + "/route?lat1=" + lat1 +
                "&lon1=" + lon1 +
                "&lat2=" + lat2 +
                "&lon2=" + lon2;
        String response = restTemplate.getForObject(fullUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<Double>> coordinatesArray = objectMapper.readValue(response, new TypeReference<List<List<Double>>>(){});
        List<Coordinate> coordinates = new ArrayList<>();
        for(List<Double> point : coordinatesArray){
            coordinates.add(new Coordinate(point.get(0), point.get(1)));
        }
        model.addAttribute("coordinates", response);

        return "courierMapOrderPage";
    }
    @GetMapping
    public String mainPage(){
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
        return "courierOrderPage";
    }
    @GetMapping("/order/{id}/finish")
    public String finishOrder(@PathVariable Integer id){
        Order order = orderService.findByStatusAndId("accepted", id);
        order.setStatus("finished");
        orderService.save(order);
        return "courierOrderPage";
    }
}
