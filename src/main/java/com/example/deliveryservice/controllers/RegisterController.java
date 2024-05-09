package com.example.deliveryservice.controllers;

import com.example.deliveryservice.dto.ClientDto;
import com.example.deliveryservice.dto.CourierDto;
import com.example.deliveryservice.entities.Courier;
import com.example.deliveryservice.services.ClientService;
import com.example.deliveryservice.services.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    private final ClientService clientService;
    private final CourierService courierService;
    @Autowired
    public RegisterController(ClientService clientService, CourierService courierService){

        this.clientService = clientService;
        this.courierService = courierService;
    }
    @GetMapping("/register")
    public String registerPage(){
        return "registerPage";
    }
    @PostMapping("/register")
    public String register(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("email") String email,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("password") String password,
                           @RequestParam("role") String role,
                            @RequestParam("transport_type") String transport){

        if(role.equals("client")){
            ClientDto clientDto = new ClientDto();
            clientDto.setFirstName(firstName);
            clientDto.setLastName(lastName);
            clientDto.setEmail(email);
            clientDto.setPhoneNumber(phoneNumber);
            clientDto.setPassword(password);
            clientService.save(clientDto);
        } else if (role.equals("courier")) {
            CourierDto courierDto = new CourierDto();
            courierDto.setFirstName(firstName);
            courierDto.setLastName(lastName);
            courierDto.setEmail(email);
            courierDto.setPhoneNumber(phoneNumber);
            courierDto.setPassword(password);
            courierDto.setTransport(transport);
            courierService.save(courierDto);
        }

        return "registerPage";
    }
}
