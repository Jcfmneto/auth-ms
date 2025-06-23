package com.example.msauth.domain.exceptions;

public class SenhaAuthInvalida extends RuntimeException {
    public SenhaAuthInvalida(String message) {
        super(message);
    }
}
