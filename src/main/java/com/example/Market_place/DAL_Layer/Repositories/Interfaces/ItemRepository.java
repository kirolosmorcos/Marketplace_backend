package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.ItemRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB1.repository.UserRepositoryDB1;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemRepository{
    @Autowired
    private ItemRepositoryDB1 ItemRepo1;

    public Item save(Item item) {
       return  ItemRepo1.save(item);
    }


    //@GetMapping
    public List<Item> findAll() {
       return ItemRepo1.findAll();

    }



    //@GetMapping("/{id}")
    public Optional<Item> findById(Long id) {
       return ItemRepo1.findById(id);
    }

    //@DeleteMapping("/{id}")
    public void deleteById( Long id) {
      ItemRepo1.deleteById(id);

    }
    public List<Item> findAllWithSpecifications() {
        return ItemRepo1.findAllWithSpecifications();
    }

}
