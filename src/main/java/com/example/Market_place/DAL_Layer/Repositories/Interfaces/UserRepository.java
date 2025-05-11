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
        user.setId(getNextId());

        if (user.getId() % 2 == 0) {
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

    public Long getNextId() {
        Long maxId1 = UserRepo1.findMaxId();
        Long maxId2 = UserRepo2.findMaxId();

        maxId1 = (maxId1 == null) ? 0L : maxId1;
        maxId2 = (maxId2 == null) ? 0L : maxId2;

        return Math.max(maxId1, maxId2) + 1;
    }
    public User UpdateUser(User user){

          User one = UserRepo1.findById(user.getId()).orElse(null);
          User two = UserRepo2.findById(user.getId()).orElse(null);
          User savedUser=null;
          if (one != null ) {
              savedUser=UserRepo1.save(user);
          }
          if (two != null ) {
              savedUser=UserRepo2.save(user);
          }
          return savedUser;
    }
    public User findByUsername(String username)
    {
        User one = UserRepo1.findByUsername(username).orElse(null);
        User two = UserRepo2.findByUsername(username).orElse(null);
        User savedUser=null;
        if (one != null ) {
            savedUser=one;
        }
        if (two != null ) {
            savedUser=two;
        }
        return savedUser;
    }
}
