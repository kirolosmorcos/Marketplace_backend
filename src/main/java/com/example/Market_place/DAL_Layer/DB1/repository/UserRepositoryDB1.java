package com.example.Market_place.DAL_Layer.DB1.repository;

import com.example.Market_place.DAL_Layer.Models.Order;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.IBaseRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryDB1 extends IBaseRepo<User,Long> {


    @Query("SELECT MAX(i.id) FROM User i ")
    Long findMaxId();
    Optional<User> findByUsername(String userName);
}
