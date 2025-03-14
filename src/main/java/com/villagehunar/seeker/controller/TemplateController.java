package com.villagehunar.seeker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.villagehunar.seeker.bean.SeekerResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/v1/temp")
public class TemplateController {

    @GetMapping("/splashscreen")
    public SeekerResponse<String> getMethodName(@RequestParam String param) {
        SeekerResponse<String> response = new SeekerResponse<>();
        
        response.setData("Test Data");

        return response;
    }
    

}
