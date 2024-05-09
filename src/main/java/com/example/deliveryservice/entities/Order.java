package com.example.deliveryservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="orders")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="client_id", referencedColumnName="id")
    private Client client;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="initial_address_id", referencedColumnName="id")
    private Address initialAddress;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="destination_address_id", referencedColumnName="id")
    private Address destinationAddress;

    @Column
    private String description;

//    @Column
//    private Double travelDistance;
//
//    @Column
//    private Long travelTime;

//    @Column
//    private Double totalAmount;

    @Column
    private String status;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="transport_type", referencedColumnName="transport_type")
    private Transport transport;

    @OneToOne(mappedBy="order")
    private Feedback feedback;

    @OneToOne(mappedBy="order")
    private AcceptedOrder acceptedOrder;
}
