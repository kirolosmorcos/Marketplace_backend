package com.example.Market_place.DAL_Layer.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private String method;
    private int amount;

    private int cardNo;
    private int CVV;
    private int month;
    private int year;


    public Long getId() {
        return paymentId;
    }
}