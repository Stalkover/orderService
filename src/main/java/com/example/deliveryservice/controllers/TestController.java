package com.example.deliveryservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/map")
    public String mapShow(){
        return "mapPage";
    }
}
