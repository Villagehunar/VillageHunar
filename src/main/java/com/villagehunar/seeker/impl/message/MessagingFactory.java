package com.villagehunar.seeker.impl.message;

public interface MessagingFactory<T> {
    Message<T> createMessage();
}