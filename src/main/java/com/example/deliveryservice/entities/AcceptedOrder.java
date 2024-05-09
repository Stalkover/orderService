package com.example.deliveryservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class AcceptedOrder {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="courier_id")
    private Courier courier;
}
