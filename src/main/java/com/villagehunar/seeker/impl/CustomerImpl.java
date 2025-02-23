package com.villagehunar.seeker.impl;

import org.springframework.stereotype.Component;

import com.villagehunar.seeker.bean.CustomerBean;
import com.villagehunar.seeker.bean.UserBean;
import com.villagehunar.seeker.impl.security.JwtService;
import com.villagehunar.seeker.impl.service.UserService;

@Component
public class CustomerImpl<T> implements UserFacade {

    private final UserService userService;
    private final JwtService jwtService;

    public CustomerImpl(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public void registerUser(CustomerBean customer) {
        userService.registerUser(customer);
    }

    @Override
    public String loginUser(String email, String password) {
        String token = userService.authenticateUser(email, password);
        
        if (token != null) {
            // Generate token on successful login
            return jwtService.generateToken(token);
        }
        return null;
    }

    @Override
    public UserBean getUserById(Long id) {
        return userService.getUserById(id);
    }
}