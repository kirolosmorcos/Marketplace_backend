package com.example.Market_place.BLL_Layer.Dto;


public class PaymentDTO {



    private double amount;
    private Long orderId;




    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    // Getters and setters

}