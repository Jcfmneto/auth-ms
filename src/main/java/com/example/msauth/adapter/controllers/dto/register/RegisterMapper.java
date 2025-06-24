package com.example.msauth.adapter.controllers.dto.register;

import com.example.msauth.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {

    public User toDomain(RegisterDto dto) {
        return new User(dto.username(), dto.password(), dto.email(), dto.bornDate());
    }

    public RegisterResponseDto toResponse(User salvo) {
        return new RegisterResponseDto(salvo.getEmail(), salvo.getUsername(), salvo.getId());
    }
}
