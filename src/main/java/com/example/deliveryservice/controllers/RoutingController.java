package com.example.deliveryservice.controllers;

import com.example.deliveryservice.utils.Coordinate;
import com.example.deliveryservice.utils.Geocoding;
import com.example.deliveryservice.utils.Points;
import com.example.deliveryservice.utils.Routing;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@Controller
public class RoutingController {
    private final Geocoding geocoding;
    private final Routing routing;
    private final RestTemplate restTemplate = new RestTemplate();
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
                          @RequestParam("destinationAddress") String destinationAddress, Model model) throws IOException, InterruptedException, ApiException {
          List<Double> initialCoords = geocoding.geocoding(initialAddress);
          List<Double> destCoords = geocoding.geocoding(destinationAddress);
          String url = "https://routing-app-dn5lmhhzmq-uc.a.run.app";
          String fullUrl = url + "/route?lat1=" + initialCoords.get(1) +
                  "&lon1=" + initialCoords.get(0) +
                  "&lat2=" + destCoords.get(1) +
                  "&lon2=" + destCoords.get(0);
        String response = restTemplate.getForObject(fullUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<Double>> coordinatesArray = objectMapper.readValue(response, new TypeReference<List<List<Double>>>(){});
        List<Coordinate> coordinates = new ArrayList<>();
        for(List<Double> point : coordinatesArray){
            coordinates.add(new Coordinate(point.get(0), point.get(1)));
        }
        model.addAttribute("coordinates", response);

//        Points points = new Points();
//        points.addPoint(geocoding.geocoding(initialAddress));
//        points.addPoint(geocoding.geocoding(destinationAddress));
//        System.out.println("All good!");
//        System.out.println(points);
//        routing.route(points);

        return "mapRoutePage";
    }
}
