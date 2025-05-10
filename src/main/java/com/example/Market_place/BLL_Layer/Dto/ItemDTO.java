package com.example.Market_place.BLL_Layer.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ItemDTO {
    private Long id;
    private String title;
    private Long sellerId;
    private String sellerName;
    private String sellerAvatar;
    private String category;
    private double price;
    private String image;
    private int quantity;
    private String description;
    private double rating;
    private String status;
    private List<SpecificationDTO> specifications;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<SpecificationDTO> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<SpecificationDTO> specifications) {
        this.specifications = specifications;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public void setCategory(String category) { this.category = category;}
    public String getCategory() { return category; }
    public Long getSellerId() {
        return sellerId;
    }
    public String getSellerName() { return sellerName; }
    public String getSellerAvatar() { return sellerAvatar; }


    public void setUserId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public void setSellerName(String sellerName) { this.sellerName= sellerName; }
    public void setSellerAvatar(String sellerAvatar) { this.sellerAvatar= sellerAvatar; }
}
