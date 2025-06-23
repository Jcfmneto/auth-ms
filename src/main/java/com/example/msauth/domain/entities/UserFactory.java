package com.example.msauth.domain.entities;

import java.time.LocalDate;

public class UserFactory {

    public static User createWithEncryptedPassword(User user, String password) {
        return new User(
                user.getUsername(),
                password,
                user.getEmail(),
                user.getBornDate()
        );
    }

    public static User withUsernamePasswordEmailBornDate(String username, String password, String email, LocalDate bornDate) {
        return new User(username, password, email, bornDate);
    }
}
