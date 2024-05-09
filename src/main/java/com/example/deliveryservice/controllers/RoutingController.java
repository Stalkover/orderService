package com.example.deliveryservice.controllers;

import com.example.deliveryservice.utils.Geocoding;
import com.example.deliveryservice.utils.Points;
import com.example.deliveryservice.utils.Routing;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class RoutingController {
    private final Geocoding geocoding;
    private final Routing routing;
    @Autowired
    public RoutingController(Geocoding geocoding, Routing routing){
        this.geocoding = geocoding;
        this.routing = routing;
    }
    @GetMapping("/routing")
    public String routingPage(){
        return "routingPage";
    }
    @PostMapping("/routing")
    public String routing(@RequestParam("initialAddress") String initialAddress,
                          @RequestParam("destinationAddress") String destinationAddress) throws IOException, InterruptedException, ApiException {
        Points points = new Points();
        points.addPoint(geocoding.geocoding(initialAddress));
        points.addPoint(geocoding.geocoding(destinationAddress));
        System.out.println("All good!");
        System.out.println(points);
        routing.route(points);

        return "routingPage";
    }
}
