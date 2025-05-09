package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.ItemRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB1.repository.UserRepositoryDB1;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository{
    private ItemRepositoryDB1 ItemRepo1;

    public Item addItem(Item item) {
       return  ItemRepo1.save(item);
    }


    //@GetMapping
    public List<Item> getAllItems() {
       return ItemRepo1.findAll();

    }



    //@GetMapping("/{id}")
    public Item getItem( Long id) {
       return ItemRepo1.findById(id).orElse(null);
    }

    //@DeleteMapping("/{id}")
    public void deleteItem( Long id) {
      ItemRepo1.deleteById(id);

    }
    public List<Item> findAllWithSpecifications() {
        return ItemRepo1.findAllWithSpecifications();
    }

}
