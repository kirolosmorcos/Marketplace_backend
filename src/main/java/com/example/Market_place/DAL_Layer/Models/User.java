package com.example.Market_place.DAL_Layer.Models;

import com.example.Market_place.DAL_Layer.enums.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @NotBlank(message = "Email is mandatory")
    @Size(min = 2, max = 30, message = "Email must be between 2 and 30 characters")
    @Email(message = "Invalid email format")
    private String username;

    private String name;



    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Pattern(
            regexp = "^[0-9]{10}$", // Adjust as needed
            message = "Phone number must be 10 digits"
    )
    private String phone;

    private String sellerAvatar;

    private double rating;

    //@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    // Foreign key in Item table
    @Transient
    private List<Item> userListings;

//    @ElementCollection
//    private List<Integer> cardInfo = new ArrayList<>();


    //@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)// Foreign key in Item table
    @Transient
    private List<Order> orders;


    @Enumerated(EnumType.STRING)  // Store the enum as a string in the database
    private RoleName role;  // The role field that will store the user's role

    private double balance;
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSellerAvatar() {
        return sellerAvatar;
    }

    public void setSellerAvatar(String sellerAvatar) {
        this.sellerAvatar = sellerAvatar;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Item> getUserListings() {
        return userListings;
    }
    public String getName() {
        return name;
    }

    public void setUserListings(List<Item> userListings) {
        this.userListings = userListings;
    }
    public User() {
        this.sellerAvatar = "default-avatar.png"; // Your default image file name or URL
    }

//    public List<Integer> getCardInfo() {
//        return cardInfo;
//    }

//    public void setCardInfo(List<Integer> cardInfo) {
//        this.cardInfo = cardInfo;
//    }


    public void setName(String name) {
        this.name = name;
    }


    //    public Vector<Integer> getCardInfo() {
//        return cardInfo;
//    }
//
//    public void setCardInfo(Vector<Integer> cardInfo) {
//        this.cardInfo = cardInfo;
//    }


}
