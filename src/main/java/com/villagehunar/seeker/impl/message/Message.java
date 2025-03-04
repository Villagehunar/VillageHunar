package com.villagehunar.seeker.impl.message;

public interface Message<T> {
    String send(T content);   
}
