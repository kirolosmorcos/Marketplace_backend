package com.example.Market_place.BLL_Layer.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ItemDTO {
    private int id;
    private String title;
    private double price;
    private String image;
    private int quantity;
    private String description;
    private double rating;
    private String status;
    private List<SpecificationDTO> specifications;
}
