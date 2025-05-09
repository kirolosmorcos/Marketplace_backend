package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.UserRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB2.repository.UserRepositoryDB2;
import com.example.Market_place.DAL_Layer.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/products")
@Service
public class UserRepository {

@Autowired
    private UserRepositoryDB1 UserRepo1;
@Autowired
    private UserRepositoryDB2 UserRepo2;
int cnt=0;
    //@PostMapping
    public User save( User user) {

        if (++cnt %2==0) {
            return UserRepo1.save(user);
        } else {
            return UserRepo2.save(user);
        }
    }


    //@GetMapping
    public List<User> findAll() {
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(UserRepo1.findAll());
        allUsers.addAll(UserRepo2.findAll());
        return allUsers;
    }



    //@GetMapping("/{id}")
    public Optional<User> findById(Long id) {
        Optional<User> one = UserRepo1.findById(id);
        Optional<User> two = UserRepo2.findById(id);
        return one .isEmpty() ? two : one;
    }

    //@DeleteMapping("/{id}")
    public void deleteById( Long id) {
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
