package com.example.Market_place.DAL_Layer.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private String image;
    private int quantity;


    private String description;
    private double rating;

    @ElementCollection
    private List<Specification> specifications;

    private String status;
    private int views;
    private LocalDate dateCreated;
}

