package com.example.msauth.domain.exceptions;

public class UsernameInvalidoException extends RuntimeException {
    public UsernameInvalidoException(String message) {
        super(message);
    }
}
