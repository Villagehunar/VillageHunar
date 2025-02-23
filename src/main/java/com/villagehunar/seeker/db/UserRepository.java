package com.villagehunar.seeker.db;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.villagehunar.seeker.bean.UserBean;

public interface UserRepository extends MongoRepository<UserBean, Long> {

    // Find a user by email
    UserBean findByEmail(String email);

    Optional<UserBean> findById(Long id);
}