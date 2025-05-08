package DAL_Layer.Models;

import jakarta.persistence.Entity;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Item {
    private int id;
    private String title;
    private double price;
    private BufferedImage image;
    private int quantity;


    private String description;
    private double rating;

    private List<Specification> specifications;

    private String status;
    private int views;
    private LocalDate dateCreated;
}

class Specification {
    private String label;
    private String value;

    public Specification(String label, String value) {
        this.label = label;
        this.value = value;
    }
}