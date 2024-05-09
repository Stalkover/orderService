package com.example.deliveryservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="couriers")
public class Courier {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String password;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="transport_type", referencedColumnName="transport_type")
    private Transport transport;

    @OneToMany(mappedBy="courier")
    private List<Feedback> feedbackList;

    @OneToMany(mappedBy="courier")
    private List<AcceptedOrder> acceptedOrders;
}
