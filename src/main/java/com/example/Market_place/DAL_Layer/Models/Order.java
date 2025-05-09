package com.example.Market_place.DAL_Layer.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    //@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)// Foreign key in Item table
    private List<Integer> itemId;
    private String paymentMethod;
    private String status;

    private LocalDate orderDate;
    private LocalDate receivedDate;
    private Double totalPrice;

    //    @ManyToOne
//    @JoinColumn(name = "buyer_id")
    private int buyerId;

    //    @ManyToOne
//    @JoinColumn(name = "payment_payment_id")
    private int paymentId;

    public int getBuyer() {
        return buyerId;
    }

    public void setBuyer(int buyer) {
        this.buyerId = buyerId;
    }

    public List<Integer> getItems() {
        return itemId;
    }

    public void setItems(List<Integer> itemId) {
        this.itemId = itemId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPayment(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}