package com.example.msauth.adapter.controllers.dto;

import java.time.LocalDate;

public record RegisterDto(String username, String password, String email, LocalDate bornDate) {

}
