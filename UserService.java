package com.flood.FloodPredictionSystem.service;

import com.flood.FloodPredictionSystem.model.User;
import com.flood.FloodPredictionSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register new user
    public boolean registerUser(User user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            return false; // Email already taken
        }
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return true;
    }

    // Login user
    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;
    }

    // Get all users
    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete user
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}