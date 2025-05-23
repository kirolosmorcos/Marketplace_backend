package com.example.Market_place.BLL_Layer.Services.Implementations;

import com.example.Market_place.BLL_Layer.Dto.UserDTO;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
import com.example.Market_place.BLL_Layer.Services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User save(User user) {
        return userRepo.save(user);

    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);

    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();

    }

    @Override
    public void deleteById(Long id) {
         userRepo.deleteById(id);
    }
    public User updateUser(User user){
         return userRepo.UpdateUser(user);
    }
    public User findUserByUserName(UserDTO userDTO){

        User user= userRepo.findByUsername(userDTO.getEmail());
        if(user==null){
            return null;
        }
        if(user.getPassword().equals(userDTO.getPassword())){
            return user;
        }
        return null;
    }

}
