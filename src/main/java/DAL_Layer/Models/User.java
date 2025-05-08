package DAL_Layer.Models;

import DAL_Layer.enums.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Setter;


import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;

@Entity
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Email is mandatory")
    @Size(min = 2, max = 30, message = "Email must be between 2 and 30 characters")
    @Email(message = "Invalid email format")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Pattern(
            regexp = "^[0-9]{10}$", // Adjust as needed
            message = "Phone number must be 10 digits"

    )
    private String phone;

    private BufferedImage sellerAvatar;


    private double rating;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id") // Foreign key in Item table
    private List<Item> userListings;

    private Vector<Integer> cardInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id") // Foreign key in Item table
        private List<Order> orders;


    @Enumerated(EnumType.STRING)  // Store the enum as a string in the database
    private RoleName role;  // The role field that will store the user's role

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public BufferedImage getSellerAvatar() {
        return sellerAvatar;
    }

    public void setSellerAvatar(BufferedImage sellerAvatar) {
        this.sellerAvatar = sellerAvatar;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Item> getUserListings() {
        return userListings;
    }

    public void setUserListings(List<Item> userListings) {
        this.userListings = userListings;
    }

    public Vector<Integer> getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(Vector<Integer> cardInfo) {
        this.cardInfo = cardInfo;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


}
