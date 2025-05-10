package com.example.Market_place.API_Layer.Controllers;

import com.example.Market_place.BLL_Layer.Dto.UserDTO;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.BLL_Layer.Services.Implementations.UserService;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
import com.example.Market_place.DAL_Layer.enums.RoleName;
import com.example.Market_place.Security.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserDTO userDto) {
//        User user = new User();
//        user.setUsername(userDto.getEmail());
//        user.setPassword(userDto.getPassword()); // hash this in service later
//        user.setRole(RoleName.ROLE_USER);
//        User savedUser = userService.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser.toString());
//    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setRole(RoleName.ROLE_USER);
        user.setBalance(0.0);

        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
            );

            // Authentication successful → fetch user + generate JWT
            User user = userService.findByUsername(userDto.getEmail()).orElseThrow();
            String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            // System.out.println("[LOGIN] Authentication failed: " + e.getMessage());
            // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/hi")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
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


//    @PostMapping("/login")
//    public ResponseEntity<UserDTO> login(UserDTO userDt) {
//        // You can log or check credentials here if needed
//
//        // Return a default user with ID 1
//        User defaultUser = new User();
//        defaultUser.setUsername("john@example.com");
//        defaultUser.setPassword("hidden"); // don't expose real passwords
//        defaultUser.setName("John Doe");
//        defaultUser.setPhone("0123456789");
//        defaultUser.setSellerAvatar("avatar.jpg");
//        defaultUser.setRating(4.5);
//        defaultUser.setBalance(1000.0);
//
//
//        User user=userService.findById(1L).get();
//
//       UserDTO userDTO=new UserDTO();
//       userDTO.setEmail(user.getUsername());
//      // userDTO.setPassword(user.getPassword());
//        userDTO.setId(user.getId());
//
//
//       userDTO.setName(defaultUser.getName());
//       userDTO.setPhone(defaultUser.getPhone());
//       userDTO.setSellerAvatar(defaultUser.getSellerAvatar());
//       userDTO.setRating(defaultUser.getRating());
//       userDTO.setBalance(defaultUser.getBalance());
//
//
//
//        return ResponseEntity.ok(userDTO);
//}

}
