package com.example.deliveryservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="addresses")
public class Address {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String address;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @OneToOne(mappedBy="initialAddress")
    private Order initialOrder;

    @OneToOne(mappedBy="destinationAddress")
    private Order destinationOrder;
}
