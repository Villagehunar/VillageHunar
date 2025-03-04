package com.villagehunar.seeker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.villagehunar.seeker.bean.CustomerBean;
import com.villagehunar.seeker.bean.SeekerResponse;
import com.villagehunar.seeker.impl.user.service.UserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/v1/user/customer")
public class UserController {
    @Autowired
    private UserFactory<CustomerBean> customerImp;

    @PostMapping("/register")
    public ResponseEntity<SeekerResponse<String>> registerUser(@RequestBody CustomerBean req) {
        customerImp.registerUser(req);
        return SeekerResponse.created("User registered successfully", "Registration complete");
    }

    @PostMapping("/login")
    public ResponseEntity<SeekerResponse<String>> loginUser(@RequestParam String email, @RequestParam String password) {
        String token = customerImp.login(email, password);
        if (token != null) {
            return SeekerResponse.success(token, "Login successful");
        }
        return SeekerResponse.error("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<SeekerResponse<CustomerBean>> getUserInfo(@PathVariable String token) {
        CustomerBean user = customerImp.getUserDetails(token);
        if(user != null){
            return SeekerResponse.error("User not found", HttpStatus.NOT_FOUND);
        }else{
            return SeekerResponse.success(user, "User found");
        }
    }

}