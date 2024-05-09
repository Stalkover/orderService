package com.example.deliveryservice.controllers;

import com.example.deliveryservice.dto.OrderDto;
import com.example.deliveryservice.entities.Address;
import com.example.deliveryservice.entities.Client;
import com.example.deliveryservice.entities.Order;
import com.example.deliveryservice.security.ClientDetails;
import com.example.deliveryservice.services.OrderService;
import com.example.deliveryservice.utils.Geocoding;
import com.example.deliveryservice.utils.Paths;
import com.example.deliveryservice.utils.Points;
import com.example.deliveryservice.utils.Routing;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/client")
@PreAuthorize("hasAuthority('ROLE_CLIENT')")
public class ClientController {
    Geocoding geocoding;
    Routing routing;
    OrderService orderService;
    @Autowired
    public ClientController(Geocoding geocoding, Routing routing, OrderService orderService){
        this.geocoding = geocoding;
        this.routing = routing;
        this.orderService = orderService;
    }

    @GetMapping("")
    public String clientPage(){
        return "clientPage";
    }
    @GetMapping("/order")
    public String listOrders(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDetails client = (ClientDetails)authentication.getPrincipal();
        List<Order> ordersList = orderService.findByClientId(client.getId());
        model.addAttribute("orders", ordersList);

        return "orderPage";
    }
    @GetMapping("/order/{id}")
    public String order(Model model, @PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDetails client = (ClientDetails)authentication.getPrincipal();
        Order order = orderService.findByClientIdAndId(client.getId(), id);
        model.addAttribute("orders", order);
        return "orderPage";
    }
    @GetMapping("/order/create")
    public String createOrder(){
        return "createOrderPage";
    }
    @PostMapping("/order/save")
    public String postCreateOrder(@RequestBody OrderDto orderDto) throws IOException, InterruptedException, ApiException {
            //orderDto.setTravelDistance(paths.getDistance());
            //orderDto.setTravelTime(paths.getTime());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            orderDto.setClient(currentPrincipalName);
            orderDto.setStatus("available");
            orderService.createOrder(orderDto);
            return "redirect:/client/order";
    }
}
