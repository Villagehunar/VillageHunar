package com.villagehunar.seeker.impl;


public interface UserStrategy<T> {
    void register(T customer);
    String login(String email, String password);
    T getUserDetail();
}