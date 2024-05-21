package com.example.deliveryservice.controllers;

import com.example.deliveryservice.security.ClientDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping
    public String redirect() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            ClientDetails client = (ClientDetails) authentication.getPrincipal();
        }catch(ClassCastException e){
            return "redirect:/courier";
        }
        return "redirect:/client";
    }
}
//    @GetMapping("/login")
//    public String login(){
//        return "loginPage";
//    }
//    @PostMapping("/login")
//    public String authenticate(@RequestParam String email,
//                                @RequestParam String password,
//                               @RequestParam String role){
//        return "loginPage";
//    }

