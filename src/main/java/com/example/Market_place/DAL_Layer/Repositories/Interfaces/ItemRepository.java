package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.Models.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends IBaseRepo<Item,Long>{
    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.specifications")
    List<Item> findAllWithSpecifications();

}
