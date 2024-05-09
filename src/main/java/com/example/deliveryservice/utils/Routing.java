package com.example.deliveryservice.utils;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class Routing {
    RestClient restClient = RestClient.create();

    String API_KEY = "ed78ef55-1102-4c3c-9a00-a8c787bbba38";
    String uriBase = "https://graphhopper.com/api/1/route?key=";

    public Paths route(Points points){
        RoutingRequest routingRequest = new RoutingRequest(points.getCoordinates(), "car");
        ResponseEntity<RoutingResponse> response = restClient.post()
                .uri(uriBase + API_KEY)
                .contentType(APPLICATION_JSON)
                .body(routingRequest)
                .retrieve()
                .toEntity(RoutingResponse.class);
        RoutingResponse routingResponse = response.getBody();
        Paths paths = routingResponse.getPaths().get(0);
        return paths;
    }
}
