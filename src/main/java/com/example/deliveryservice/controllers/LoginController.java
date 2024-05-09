package com.example.deliveryservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "loginPage";
    }
    @PostMapping("/login")
    public String authenticate(@RequestParam String email,
                                @RequestParam String password,
                               @RequestParam String role){
        return "loginPage";
    }

}
