package com.villagehunar.seeker.impl.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.villagehunar.seeker.bean.CustomerBean;
import com.villagehunar.seeker.category.UserType;
import com.villagehunar.seeker.db.CustomerRepository;
import com.villagehunar.seeker.exception.SeekerException;
import com.villagehunar.seeker.impl.user.service.UserFactory;
import com.villagehunar.seeker.security.JwtService;

@Component
public class CustomerImp implements UserFactory<CustomerBean>{

    private final CustomerRepository repo;
    private final JwtService jwt;

    public CustomerImp(CustomerRepository repo, JwtService jwt){
        this.repo = repo;
        this.jwt = jwt;
    }

    @Override
    public String login(String userId, String password) throws SeekerException{
        //decode the encrypted password

        CustomerBean user = repo.findByUsernameAndPassword(userId,password)
        .orElseThrow(()->new SeekerException("No User found"));
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", UserType.CUSTOMER.name());
        claims.put("email", user.getEmail());
        return jwt.generateToken(claims, user.getUsername());
    }


    @Override
    public void registerUser(CustomerBean user) {
        repo.save(user);
    }

    @Override
    public CustomerBean getUserDetails(String token) {
         String username = jwt.extractUsername(token);
        return repo.findByUsername(username)
                .orElseThrow(() -> new SeekerException("User not found for token"));
    }
    
}
