
package com.example.Market.Place.Service;

import com.example.Market.Place.Models.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;

public class ItemService {

    private final EntityManager entityManager;

    public ItemService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Method to add an item to the database
    public void addItem(String title, double price, int quantity, String description, double rating, String status, int views) {
        // Create a new Item instance
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setDescription(description);
        item.setRating(rating);
        item.setStatus(status);
        item.setViews(views);
        item.setDateCreated(LocalDate.now());  // Set the creation date as the current date

        // Persist the item to the database
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(item);  // Save the new item to the database
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();  // Rollback if something goes wrong
            e.printStackTrace();
        }
    }
}

