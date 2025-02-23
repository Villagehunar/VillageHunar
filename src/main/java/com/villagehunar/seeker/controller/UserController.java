package com.villagehunar.seeker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.villagehunar.seeker.bean.CustomerBean;
import com.villagehunar.seeker.bean.SeekerResponse;
import com.villagehunar.seeker.bean.UserBean;
import com.villagehunar.seeker.impl.UserFacade;
import com.villagehunar.seeker.impl.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private final UserFacade userFacade;
    @Autowired
    private UserService userService;

    
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<SeekerResponse<String>> registerUser(@RequestBody CustomerBean customer) {
        userFacade.registerUser(customer);
        return SeekerResponse.created("User registered successfully", "Registration complete");
    }

    @PostMapping("/login")
    public ResponseEntity<SeekerResponse<String>> loginUser(@RequestParam String email, @RequestParam String password) {
        String token = userFacade.loginUser(email, password);
        if (token != null) {
            return SeekerResponse.success(token, "Login successful");
        }
        return SeekerResponse.error("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<SeekerResponse<UserBean>> getUserInfo(@PathVariable Long id) {
        UserBean user = userFacade.getUserById(id);
        if(user != null){
            return SeekerResponse.error("User not found", HttpStatus.NOT_FOUND);
        }else{
            return SeekerResponse.success(user, "User found");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, 
                                            @RequestParam String password, 
                                            @RequestParam String userType) {
        String token = userService.loginUser(email, password, userType);
        return ResponseEntity.ok(token);
    }

}