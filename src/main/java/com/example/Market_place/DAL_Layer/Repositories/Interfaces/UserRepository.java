package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.UserRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB2.repository.UserRepositoryDB2;
import com.example.Market_place.DAL_Layer.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/products")

public class UserRepository {


    private UserRepositoryDB1 UserRepo1;
    private UserRepositoryDB2 UserRepo2;

    //@PostMapping
    public User addUser( User user) {
        if (user.getId() % 2 == 0) {
            return UserRepo1.save(user);
        } else {
            return UserRepo2.save(user);
        }
    }


    //@GetMapping
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(UserRepo1.findAll());
        allUsers.addAll(UserRepo2.findAll());
        return allUsers;
    }



    //@GetMapping("/{id}")
    public User getUser( Long id) {
        User one = UserRepo1.findById(id).orElse(null);
        User two = UserRepo2.findById(id).orElse(null);
        return one == null ? two : one;
    }

    //@DeleteMapping("/{id}")
    public void deleteUser( Long id) {
        User one = UserRepo1.findById(id).orElse(null);
        User two = UserRepo2.findById(id).orElse(null);
        if (one != null ) {
            UserRepo1.deleteById(id);
        }
        if (two != null ) {
            UserRepo2.deleteById(id);
        }

    }
}
