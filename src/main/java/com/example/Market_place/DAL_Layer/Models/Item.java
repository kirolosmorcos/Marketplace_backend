package com.example.Market_place.DAL_Layer.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class Item {

    @Id
    private Long id;

    private String title;
    private double price;
    private String image;
    private int quantity;

//    public int getQuantitySold() {
//        return quantitySold;
//    }
//
//    public void setQuantitySold(int quantitySold) {
//        this.quantitySold = quantitySold;
//    }
//
//    private int quantitySold;


    private String description;
    private double rating;
    @Column(nullable = false)
    private LocalDate dateCreated;

    //    @ManyToOne
//    @JoinColumn(name = "seller_id")
    private int sellerId;

    //    @ManyToOne
//    @JoinColumn(name = "order_id")
    private int orderId;

   // @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
   @Transient
    private List<Specification> specifications;

    private String status;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    private int views;

    @PrePersist
    public void prePersist() {
        if (this.dateCreated == null) {
            this.dateCreated = LocalDate.now();  // Set current date when the entity is persisted
        }
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
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

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }



   // private LocalDate dateCreated;

    public int getSellerId() {
        return sellerId;
    }
}

