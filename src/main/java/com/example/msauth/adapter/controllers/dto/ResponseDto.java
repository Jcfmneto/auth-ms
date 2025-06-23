package com.example.msauth.adapter.controllers.dto;

import java.util.UUID;

public record ResponseDto(String email, String username, UUID id) {
}
