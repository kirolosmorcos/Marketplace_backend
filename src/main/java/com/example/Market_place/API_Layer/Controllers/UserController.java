package com.example.Market_place.API_Layer.Controllers;

import com.example.Market_place.BLL_Layer.Dto.UserDTO;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.BLL_Layer.Services.Implementations.UserService;
import com.example.Market_place.DAL_Layer.enums.RoleName;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserDTO userDto) {
//        // logic to register user
//        return ResponseEntity.ok("User registered");
//    }



    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getEmail());
        user.setRole(RoleName.ROLE_USER);
        user.setBalance(0.0);

        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
      //  return ResponseEntity.ok("Registered!");
    }

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


    @PostMapping("/hi")
    public String logout() {
        return "Logout successful!";
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = optionalUser.get();
        existingUser.setUsername(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setSellerAvatar(userDTO.getSellerAvatar());
        existingUser.setRating(userDTO.getRating());

        //existingUser.setBalance(userDTO.getBalance());


        User updatedUser = userService.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login() {
        // You can log or check credentials here if needed

        // Return a default user with ID 1
        UserDTO defaultUser = new UserDTO();
        defaultUser.setEmail("john@example.com");
        defaultUser.setPassword("hidden"); // don't expose real passwords
        defaultUser.setName("John Doe");
        defaultUser.setPhone("0123456789");
        defaultUser.setSellerAvatar("avatar.jpg");
        defaultUser.setRating(4.5);

        return ResponseEntity.ok(defaultUser);
}

}
