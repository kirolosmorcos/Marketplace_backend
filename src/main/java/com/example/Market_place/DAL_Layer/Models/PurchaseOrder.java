package com.example.Market_place.DAL_Layer.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity

public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // Foreign key in Item table
    private List<Item> Items;
    private String paymentMethod;
    private String status;

    private LocalDate orderDate;
    private LocalDate receivedDate;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    @ManyToOne
    @JoinColumn(name = "payment_payment_id")
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
