package com.example.Market_place.DAL_Layer.DB1.repository;

import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.IBaseRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositoryDB1 extends IBaseRepo<Item,Long> {
//    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.specifications")
    List<Item> findAllWithSpecifications();
    List<Item> findBySellerId(Long sellerId);
}
