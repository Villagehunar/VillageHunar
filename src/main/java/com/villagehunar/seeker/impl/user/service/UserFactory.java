package com.villagehunar.seeker.impl.user.service;

public interface UserFactory<USER_BEAN> {
    void registerUser(USER_BEAN user);
    String login(String userid, String password);
    USER_BEAN getUserDetails(String token);
}
