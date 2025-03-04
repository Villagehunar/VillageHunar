package com.villagehunar.seeker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/v1/validate")
public class ValidationController {
    @PostMapping("/email")
    public String send(@RequestBody String entity) {
        
        
        return entity;
    }
    
}
