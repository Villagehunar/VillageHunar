package com.villagehunar.seeker.impl.message;

import org.springframework.stereotype.Component;

@Component
public class EmailFactory implements MessagingFactory<String> {

    @Override
    public Message<String> createMessage() {
        return new EmailMessage();   
    }
    
}
