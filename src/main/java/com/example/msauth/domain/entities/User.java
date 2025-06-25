package com.example.msauth.domain.entities;

import com.example.msauth.domain.exceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class
User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,}$";

    public User(String username, String password, String email) {
        validarUsername(username);
        validarEmail(email);
        validarPassword(password);


        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    private void validarUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new UsernameInvalidoException("Username não pode ser vazio.");
        }
    }

    private void validarEmail(String email) {
        if (email == null || !email.matches(EMAIL_REGEX)) {
            throw new EmailInvalidoException("Formato de email inválido: " + email);
        }
    }

    private void validarPassword(String password) {
        if (password == null || !password.matches(PASSWORD_REGEX)) {
            throw new SenhaInvalidaException("Senha inválida. Deve ter pelo menos 8 caracteres, uma letra maiúscula, uma minúscula e um caractere especial.");
        }
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
