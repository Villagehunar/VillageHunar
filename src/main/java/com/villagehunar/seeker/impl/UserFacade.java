package com.villagehunar.seeker.impl;

import com.villagehunar.seeker.bean.CustomerBean;
import com.villagehunar.seeker.bean.UserBean;

public interface UserFacade {
    void registerUser(CustomerBean customer);
    String loginUser(String email, String password);
    UserBean getUserById(Long id);
}