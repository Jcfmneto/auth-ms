package com.example.msauth.domain.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email) {
        super("Could not find user with email " + email);
    }
}
