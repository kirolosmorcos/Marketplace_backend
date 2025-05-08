package com.example.Market.Place;

import com.example.Market.Place.Service.ItemService;
import com.example.Market.Place.Models.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketPlaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketPlaceApplication.class, args);
	}
	// Create the EntityManagerFactory
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");
	EntityManager em = emf.createEntityManager();

	// Create an ItemService object
	ItemService itemService = new ItemService(em);

	// Call addItem method to insert an item
        itemService.addItem("Sample Product", 29.99, 10, "A sample product description", 4.7, "Available", 100);

        em.close();
        emf.close();
}
