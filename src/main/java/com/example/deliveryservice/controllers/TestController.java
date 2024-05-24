package com.example.deliveryservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class TestController {
    @GetMapping("/map")
    public String mapShow(){
        return "mapPage";
    }
}
