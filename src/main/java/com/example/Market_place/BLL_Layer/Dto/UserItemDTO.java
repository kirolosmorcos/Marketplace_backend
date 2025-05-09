package com.example.Market_place.BLL_Layer.Dto;

public class UserItemDTO {

    private Long id;
    private String title;
    private double price;
    private String status;
    private String image;
    private int views;
    private String created;

//    public int getQuantitySold() {
//        return quantitySold;
//    }
//
//    public void setQuantitySold(int quantitySold) {
//        this.quantitySold = quantitySold;
//    }
//
//    private int quantitySold;


    // Getters and setters for these fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public  String getDateCreated() {
        return created;
    }

    public void setDateCreated(String created) {
        this.created = created;
    }
}


