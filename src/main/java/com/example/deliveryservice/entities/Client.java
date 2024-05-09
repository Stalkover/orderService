package com.example.deliveryservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="clients")
public class Client{
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

    @OneToMany(mappedBy="client")
    private List<Feedback> feedback;

    @OneToMany(mappedBy="client")
    private List<Order> orders;
}
