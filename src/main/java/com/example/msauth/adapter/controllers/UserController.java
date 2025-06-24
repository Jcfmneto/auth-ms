package com.example.msauth.adapter.controllers;


import com.example.msauth.adapter.controllers.dto.RegisterDto;
import com.example.msauth.adapter.controllers.dto.ResponseDto;
import com.example.msauth.adapter.service.AuthApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid RegisterDto dto, UriComponentsBuilder uriComponentsBuilder){
        ResponseDto usuario = authApplicationService.register(dto);
        URI uri = uriComponentsBuilder.path("/auth/register/" + usuario.id()).build().toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

}

