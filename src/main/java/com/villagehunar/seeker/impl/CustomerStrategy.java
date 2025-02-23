package com.villagehunar.seeker.impl;


import com.villagehunar.seeker.bean.CustomerBean;
import com.villagehunar.seeker.bean.UserBean;
import com.villagehunar.seeker.db.UserRepository;
import com.villagehunar.seeker.impl.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service("CUSTOMER")
public class CustomerStrategy implements UserStrategy<CustomerBean> {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public CustomerStrategy(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void register(CustomerBean customer) {
        if (userRepository.findByEmail(customer.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists for customer");
        }
        userRepository.save(customer);
    }

    @Override
    public String login(String email, String password) {
        UserBean userOptional = userRepository.findByEmail(email);
        if (userOptional != null && userOptional.getPassword().equals(password)) {
            return jwtService.generateToken(email);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid customer credentials");
    }

    @Override
    public CustomerBean getUserDetail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserDetail'");
    }
}