package com.example.deliveryservice.utils;

import lombok.Data;

import java.util.List;

@Data
public class RoutingRequest {
    private List<List<Double>> points;
    private String profile;
    private Boolean instructions = false;
    private Boolean points_encoded = false;
    public RoutingRequest(List<List<Double>> points, String profile){
        this.points = points;
        this.profile = profile;
    }
}
