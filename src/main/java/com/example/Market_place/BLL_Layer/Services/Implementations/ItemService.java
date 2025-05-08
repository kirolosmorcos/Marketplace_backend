package com.example.Market_place.BLL_Layer.Services.Implementations;

import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.ItemRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements com.example.Market_place.BLL_Layer.Services.Interfaces.IItemService {
    @Autowired
    private ItemRepository itemRepo;

    @Override
    public Item save(Item item) {
        return itemRepo.save(item);
    }
    @Override
    public void deleteById(Long id) {
        itemRepo.deleteById(id);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepo.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return itemRepo.findAll();
    }

}
