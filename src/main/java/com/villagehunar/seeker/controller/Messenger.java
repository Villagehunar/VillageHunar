package com.villagehunar.seeker.controller;

import org.apache.el.util.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.villagehunar.seeker.impl.message.EmailFactory;
import com.villagehunar.seeker.impl.message.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/v1/send")
public class Messenger {
    private final Message<String> emailMessage;

    public Messenger(EmailFactory emailFactory) {
        this.emailMessage = emailFactory.createMessage();
    }

    @PostMapping("/email")
    public String postMethodName(@RequestBody String email) {
        return emailMessage.send(email);
    }
    
}
