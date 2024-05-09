package com.example.deliveryservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="feedback")
public class Feedback {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer rating;

    @Column
    private String comment;

    //TIMESTAMP implementation required
    @Column
    private String timestamp;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="client_id", referencedColumnName="id")
    private Client client;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName="id")
    private Order order;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="courier_id", referencedColumnName="id")
    private Courier courier;
}
