package com.example.msauth.adapter.controllers;


import com.example.msauth.adapter.controllers.dto.login.LoginDto;
import com.example.msauth.adapter.controllers.dto.login.LoginResponseDto;
import com.example.msauth.adapter.controllers.dto.register.RegisterDto;
import com.example.msauth.adapter.controllers.dto.register.RegisterResponseDto;
import com.example.msauth.adapter.service.AuthApplicationService;
import com.example.msauth.infra.config.security.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthApplicationService authApplicationService;

    public UserController(AuthApplicationService authApplicationService) {
        this.authApplicationService = authApplicationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterDto dto, UriComponentsBuilder uriComponentsBuilder){
        RegisterResponseDto usuario = authApplicationService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto dto){
        String token = authApplicationService.login(dto);
        LoginResponseDto response = new LoginResponseDto(token);
        return ResponseEntity.ok(response);
    }
}

