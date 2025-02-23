package com.villagehunar.seeker.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Document(collection = "users")
public abstract class UserBean {
    @Id
    protected Long id;
    protected String username;
    protected String fName;
    protected String lName;
    protected String email;
    protected String password;
    protected String userRole;
    protected String userType;
}
