package com.example.msauth.adapter.controllers.dto;

import com.example.msauth.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    public User toDomain(RegisterDto dto) {
        return new User(dto.username(), dto.password(), dto.email(), dto.bornDate());
    }

    public ResponseDto toResponse(User salvo) {
        return new ResponseDto(salvo.getEmail(), salvo.getUsername(), salvo.getId());
    }
}
