package com.easypay.api.service;

import com.easypay.api.model.User;
import com.easypay.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserByEmail(String email) throws Exception{
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found " + email));
    }

}
