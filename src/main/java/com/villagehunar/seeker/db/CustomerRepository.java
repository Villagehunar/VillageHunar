package com.villagehunar.seeker.db;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.villagehunar.seeker.bean.CustomerBean;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerBean,Long>{
    Optional<CustomerBean> findById(Long id);
    Optional<CustomerBean> findByUsername(String username);
    Optional<CustomerBean> findByUsernameAndPassword(String username, String password);
}
