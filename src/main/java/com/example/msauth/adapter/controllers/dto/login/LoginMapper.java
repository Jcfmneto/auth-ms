package com.example.msauth.adapter.controllers.dto.login;

import com.example.msauth.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public User toDomain(LoginDto dto) {
        return new User(dto.email(), dto.password());
    }
}
