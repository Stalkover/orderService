package com.example.deliveryservice.utils;

import lombok.Data;

import java.util.List;

@Data
public class Paths {
    private double distance;
    private double weight;
    private long time;
    private int transfers;
    private boolean points_encoded;
    private List<Double> bbox;
    private Points points;
}
