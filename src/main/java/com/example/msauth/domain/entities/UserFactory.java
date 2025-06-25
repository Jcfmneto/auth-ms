package com.example.msauth.domain.entities;


public class UserFactory {

    public static User createWithEncryptedPassword(User user, String password) {
        return new User(
                user.getUsername(),
                password,
                user.getEmail(), user.getId());
    }

    public static User withUsernamePasswordEmailBornDate(String username, String password, String email) {
        return new User(username, password, email);
    }
}
