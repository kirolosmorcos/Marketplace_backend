package com.example.Market_place.DAL_Layer.DB2.repository;

import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.IBaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositoryDB2 extends IBaseRepo<Item,Long> {
}
