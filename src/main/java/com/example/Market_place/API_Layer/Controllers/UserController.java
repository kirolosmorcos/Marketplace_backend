package com.example.Market_place.API_Layer.Controllers;

import com.example.Market_place.BLL_Layer.Dto.UserDTO;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.BLL_Layer.Services.Implementations.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDto) {
        // logic to register user
        return ResponseEntity.ok("User registered");
    }



//    @PostMapping("/register")
//    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
//        User user = new User();
//        user.setPassword(userDTO.getPassword());
//        user.setUsername(userDTO.getEmail());
//        user.setRole(RoleName.ROLE_USER);
//
//        User savedUser = userService.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
//      //  return ResponseEntity.ok("Registered!");
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public String login() {
        return "Login successful!";
    }

    @PostMapping("/hi")
    public String logout() {
        return "Logout successful!";
    }

}
