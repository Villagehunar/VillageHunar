package com.villagehunar.seeker.impl.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.villagehunar.seeker.bean.CustomerBean;
import com.villagehunar.seeker.bean.UserBean;
import com.villagehunar.seeker.db.UserRepository;
import com.villagehunar.seeker.impl.UserStrategy;
import com.villagehunar.seeker.impl.security.JwtService;

import java.util.Map;
import java.util.Optional;

public class UserService {

    private final Map<String, UserStrategy> strategies;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(Map<String, UserStrategy> strategies, UserRepository userRepository, JwtService jwtService) {
        this.strategies = strategies;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    public String authenticateUser(String email, String password) {
        UserBean user = userRepository.findByEmail(email);

        if (user != null) {

            // In real-world apps, you should hash and compare the password (e.g., BCrypt)
            if (user.getPassword().equals(password)) {
                return jwtService.generateToken(email);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public void registerUser(CustomerBean customer) {
        String userType = customer.getUserType();
        UserStrategy strategy = strategies.get(userType.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported user type: " + userType);
        }
        strategy.register(customer);
    }

    public String loginUser(String email, String password, String userType) {
        UserStrategy strategy = strategies.get(userType.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported user type: " + userType);
        }
        return strategy.login(email, password);
    }

    public UserBean getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
