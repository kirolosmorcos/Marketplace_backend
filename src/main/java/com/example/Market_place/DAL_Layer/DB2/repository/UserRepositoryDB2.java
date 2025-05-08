package com.example.Market_place.DAL_Layer.DB2.repository;

import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.IBaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryDB2 extends IBaseRepo<User,Long> {
}
