package com.example.deliveryservice.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
public class Points {
    private List<List<Double>> coordinates = new ArrayList<>();
    public void addPoint(Double latitude, Double longitude){
        List<Double> point = new ArrayList<>();
        point.add(longitude);
        point.add(latitude);
        coordinates.add(point);
    }
    public void addPoint(List<Double> point){
        coordinates.add(point);
    }
}
