package com.example.Market_place.API_Layer.Controllers;

import com.example.Market_place.BLL_Layer.Dto.UserDTO;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.BLL_Layer.Services.Implementations.UserService;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
import com.example.Market_place.DAL_Layer.enums.RoleName;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
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
        user.setName(userDTO.getName());
        user.setRole(RoleName.ROLE_USER);
        user.setBalance(0.0);

        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
      //  return ResponseEntity.ok("Registered!");
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
//        Optional<User> user = userService.findById(id);
//        UserDTO userDTO=new UserDTO();
//        userDTO.setEmail(user.get().getUsername());
//        userDTO.setPassword(user.get().getPassword());
//        userDTO.setName(user.get().getName());
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

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
        if(userDTO.getEmail()!=null)
        existingUser.setUsername(userDTO.getEmail());

        if(userDTO.getPassword()!=null)
        existingUser.setPassword(userDTO.getPassword());

        if(userDTO.getPhone()!=null)
        existingUser.setPhone(userDTO.getPhone());

        if(userDTO.getSellerAvatar()!=null)
        existingUser.setSellerAvatar(userDTO.getSellerAvatar());

        if( userDTO.getName()!=null)
        existingUser.setName(userDTO.getName());

        if(userDTO.getBalance()!=0)
        existingUser.setBalance(userDTO.getBalance());

        if(userDTO.getRating()!=0)
            existingUser.setRating(userDTO.getRating());

        //existingUser.setBalance(userDTO.getBalance());


        User updatedUser = userService.updateUser(existingUser);
        return ResponseEntity.ok(updatedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(UserDTO userDt) {
        // You can log or check credentials here if needed

        // Return a default user with ID 1
//        User defaultUser = new User();
//        defaultUser.setUsername("john@example.com");
//        defaultUser.setPassword("hidden"); // don't expose real passwords
//        defaultUser.setName("John Doe");
//        defaultUser.setPhone("0123456789");
//        defaultUser.setSellerAvatar("avatar.jpg");
//        defaultUser.setRating(4.5);
//        defaultUser.setBalance(1000.0);


//        User user=userService.findById(1L).get();
//
//       UserDTO userDTO=new UserDTO();
//       userDTO.setEmail(user.getUsername());
//      // userDTO.setPassword(user.getPassword());
//        userDTO.setId(user.getId());


//       userDTO.setName(defaultUser.getName());
//       userDTO.setPhone(defaultUser.getPhone());
//       userDTO.setSellerAvatar(defaultUser.getSellerAvatar());
//       userDTO.setRating(defaultUser.getRating());
//       userDTO.setBalance(defaultUser.getBalance());

        User user = userService.findUserByUserName(userDt);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        UserDTO userDTO=new UserDTO();
        userDTO.setEmail(user.getUsername());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());


        return ResponseEntity.ok(userDTO);
}

}
