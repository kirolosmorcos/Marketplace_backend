package com.example.Market_place.BLL_Layer.Services.Interfaces;

import com.example.Market_place.DAL_Layer.Models.User;

import java.util.Optional;

public interface IUserService extends IBaseService<User,Long> {
    Optional<User> findByUsername(String username);
}
