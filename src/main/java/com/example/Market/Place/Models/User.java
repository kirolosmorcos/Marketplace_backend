package com.example.Market.Place.Models;

import enums.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is mandatory")
    @Size(min = 2, max = 30, message = "Email must be between 2 and 30 characters")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @Lob
    private byte[] sellerAvatar; // Store as binary data (BLOB)

    private double rating;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private List<Item> userListings = new ArrayList<>();

    @ElementCollection
    private List<Integer> cardInfo = new ArrayList<>(); // Use List instead of Vector

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id")
    private List<Order> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RoleName role;
}
