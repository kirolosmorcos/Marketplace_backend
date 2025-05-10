package com.example.Market_place.DAL_Layer.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Long sellerId;
    @Transient
    private User seller;

    //    @ManyToOne
//    @JoinColumn(name = "order_id")
    private Long orderId;
    @Transient
    private Order order;

   // @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
   @Transient
    private List<Specification> specifications;

    private String status;
    private String category;
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    private int views;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

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

    public Long getSellerId() {
        return sellerId;
    }
}

