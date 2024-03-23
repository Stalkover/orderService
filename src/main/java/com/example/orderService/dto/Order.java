package com.example.orderService.dto;

import jakarta.persistence.*;

@Entity
@Table(name="order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int orderID;
    public String initialAddress;
    public String destinationAddress;
    public String items;
    public Order(){}
    public Order(String initialAddress, String destinationAddress, String items){
        this.destinationAddress = destinationAddress;
        this.initialAddress = initialAddress;
        this.items = items;
    }
}
