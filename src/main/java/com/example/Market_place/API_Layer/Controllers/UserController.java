package com.example.Market_place.API_Layer.Controllers;

import com.example.Market_place.BLL_Layer.Dto.UserDTO;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.BLL_Layer.Services.Implementations.UserService;
import com.example.Market_place.DAL_Layer.enums.RoleName;
import com.example.Market_place.Security.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

//TODO: delete

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        System.out.println("[REGISTER] Email: " + userDTO.getEmail());
        System.out.println("[REGISTER] Raw password: " + userDTO.getPassword());

        User user = new User();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getEmail());
        user.setRole(RoleName.ROLE_USER);

        User savedUser = userService.save(user);
        System.out.println("[REGISTER] Encoded password: " + savedUser.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDto) {
        System.out.println("[LOGIN] Email: " + userDto.getEmail());
        System.out.println("[LOGIN] Password: " + userDto.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
            );

            System.out.println("[LOGIN] Authentication successful!");

            User user = userService.findByUsername(userDto.getEmail()).orElseThrow();
            String token = jwtService.generateToken(user.getUsername(), user.getRole().name());

            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            System.out.println("[LOGIN] Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

//    @PostMapping("/register")
//    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
//        User user = new User();
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        user.setUsername(userDTO.getEmail());
//        user.setRole(RoleName.ROLE_USER);
//
//        User savedUser = userService.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserDTO userDto) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
//            );
//
//            // Authentication successful → fetch user + generate JWT
//            User user = userService.findByUsername(userDto.getEmail()).orElseThrow();
//            String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
//            return ResponseEntity.ok(token);
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }




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


//    @PostMapping("/login")
//    public String login() {
//        return "Login successful!";
//    }

    @GetMapping("/hi")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String hi() {
        return "Hello, authorized user!";
    }

}
