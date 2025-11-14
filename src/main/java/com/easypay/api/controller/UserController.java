package com.easypay.api.controller;

import com.easypay.api.model.User;
import com.easypay.api.model.enums.Role;
import com.easypay.api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            if (userRepository.existsByEmail(user.getEmail())) {
                return ResponseEntity.badRequest().body("Email is already registered!");
            }

            if (user.getRoles() == null) {
                user.setRoles(Set.of(Role.USER));
            }

            if (user.getPassword() == null || user.getPassword().isBlank()) {
                return ResponseEntity.badRequest().body("Password is required!");
            }

            userRepository.save(user);
            return ResponseEntity.ok().body("User registered successfully!");
        }catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Registration failed: " + ex.getMessage());
        }

    }

}
