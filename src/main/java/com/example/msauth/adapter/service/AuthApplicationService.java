package com.example.msauth.adapter.service;

import com.example.msauth.adapter.controllers.dto.login.LoginDto;
import com.example.msauth.adapter.controllers.dto.login.LoginMapper;
import com.example.msauth.adapter.controllers.dto.register.RegisterDto;
import com.example.msauth.adapter.controllers.dto.register.RegisterMapper;
import com.example.msauth.adapter.controllers.dto.register.RegisterResponseDto;
import com.example.msauth.application.usecases.CreateUserUseCase;
import com.example.msauth.application.usecases.LoginUseCase;
import com.example.msauth.domain.entities.User;
import com.example.msauth.infra.persistence.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthApplicationService {

    private final UserMapper userMapper;
    private final LoginUseCase loginUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final RegisterMapper registerMapper;
    private final LoginMapper loginMapper;


    public AuthApplicationService(UserMapper userMapper, LoginUseCase loginUseCase, CreateUserUseCase createUserUseCase, RegisterMapper registerMapper, LoginMapper loginMapper) {
        this.userMapper = userMapper;
        this.loginUseCase = loginUseCase;
        this.createUserUseCase = createUserUseCase;
        this.registerMapper = registerMapper;
        this.loginMapper = loginMapper;
    }

    public RegisterResponseDto register(RegisterDto dto) {
        User user = registerMapper.toDomain(dto);
        User salvo = createUserUseCase.execute(user);
        return registerMapper.toResponse(salvo);
    }
    public String login(LoginDto dto) {
        User user = loginMapper.toDomain(dto);
        return loginUseCase.execute(user);
    }
}
