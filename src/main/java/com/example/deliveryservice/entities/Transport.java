package com.example.deliveryservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="transport")
public class Transport {
    @Id
    @Column(name="transport_type")
    private String transportType;

    @Column
    private Double rate;

    @OneToMany(mappedBy="transport")
    private List<Courier> courier;

    @OneToMany(mappedBy="transport")
    private List<Order> order;
}
