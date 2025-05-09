package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.ItemRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB2.repository.ItemRepositoryDB2;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Specification;
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
    //add static variabl for id instead of quering every time

    @Autowired
    private ItemRepositoryDB1 ItemRepo1;

    @Autowired
    private ItemRepositoryDB2 ItemRepo2;

    @Autowired
    private SpecificationRepository SpecRepo;

    public Item save(Item item) {
        item.setId(getNextId());


        List<Specification>specs=item.getSpecifications();
        for(Specification spec:specs)
        {
            spec.setItemId(item.getId());
            SpecRepo.save(spec);
        }
        if (item.getSellerId() % 2 == 0) {
            return ItemRepo1.save(item);  // Even ID → DB1
        } else {
            return ItemRepo2.save(item);  // Odd ID → DB2
        }
    }


    //@GetMapping
    public List<Item> findAll() {
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(ItemRepo1.findAll());
        allItems.addAll(ItemRepo2.findAll());
        return allItems;
    }



    //@GetMapping("/{id}")
    public Optional<Item> findById(Long id) {
        Optional<Item> item = ItemRepo1.findById(id);
        if (item.isPresent()) {
            return item;
        }
        Optional<Item> item2 = ItemRepo2.findById(id);
        return item2;
    }

    //@DeleteMapping("/{id}")
    public void deleteById(Long id) {
        Item one = ItemRepo1.findById(id).orElse(null);
        Item two = ItemRepo2.findById(id).orElse(null);
        if (one != null) {
            ItemRepo1.deleteById(id);
        }
        if (two != null) {
            ItemRepo2.deleteById(id);
        }

    }

    public List<Item> findAllWithSpecifications() {//need implementation with join
        List<Item> items = new ArrayList<>();

        items.addAll(ItemRepo1.findAll());
        items.addAll(ItemRepo2.findAll());
        for(Item item:items)
        {
            Long itemId=item.getId();
            List<Specification> specs=SpecRepo.findAllByItemId(itemId);
            item.setSpecifications(specs);
        }
        return items;
    }
   public void UpdateItem(Item item){//need implementation
       Item one = ItemRepo1.findById(item.getId()).orElse(null);
       Item two = ItemRepo2.findById(item.getId()).orElse(null);
       if (one != null) {
           ItemRepo1.save(item);
       }
       if (two != null) {
           ItemRepo2.save(item);
       }
   }

    public List<Item> findBySellerId(Long userId) {
        List<Item> items = new ArrayList<>();
        items.addAll(ItemRepo1.findBySellerId(userId));
        items.addAll(ItemRepo2.findBySellerId(userId));
        return items;
    }
    public List<Item>findByOrderId(Long orderId){
        List<Item> items=new ArrayList<>();
        items.addAll(ItemRepo1.findByOrderId(orderId));
        items.addAll(ItemRepo2.findByOrderId(orderId));
        return items;
    }

    public Long getNextId() {
        Long maxId1 = ItemRepo1.findMaxId();
        Long maxId2 = ItemRepo2.findMaxId();

        maxId1 = (maxId1 == null) ? 0L : maxId1;
        maxId2 = (maxId2 == null) ? 0L : maxId2;

        return Math.max(maxId1, maxId2) + 1;
    }

}
