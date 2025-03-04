package com.villagehunar.seeker.db;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.villagehunar.seeker.bean.UserBean;

@Repository
public interface UserRepository extends MongoRepository<UserBean, Long> {

    // Find a user by email
    UserBean findByEmail(String email);

    Optional<UserBean> findById(Long id);
    Optional<UserBean> findByUsername(String username);
    Optional<UserBean> findByUsernameAndPassword(String username, String password);
}