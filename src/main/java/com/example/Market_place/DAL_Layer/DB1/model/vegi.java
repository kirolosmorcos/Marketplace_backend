package com.example.Market_place.DAL_Layer.DB1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class vegi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int price;
    private String name;
}
