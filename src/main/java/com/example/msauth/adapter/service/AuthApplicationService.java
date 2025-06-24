package com.example.msauth.adapter.service;

import com.example.msauth.adapter.controllers.dto.login.LoginDto;
import com.example.msauth.adapter.controllers.dto.login.LoginMapper;
import com.example.msauth.adapter.controllers.dto.register.RegisterDto;
import com.example.msauth.adapter.controllers.dto.register.RegisterMapper;
import com.example.msauth.adapter.controllers.dto.register.RegisterResponseDto;
import com.example.msauth.application.usecases.CreateUserUseCaseImp;
import com.example.msauth.application.usecases.LoginUseCaseImp;
import com.example.msauth.domain.entities.User;
import com.example.msauth.domain.gateways.PasswordEncoderGateway;
import com.example.msauth.infra.gateway.UserGatewayImpl;
import com.example.msauth.infra.persistence.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthApplicationService {

    private final UserMapper userMapper;
    private final LoginUseCaseImp loginUseCaseImp;
    private final CreateUserUseCaseImp createUserUseCaseImp;
    private final RegisterMapper registerMapper;
    private final LoginMapper loginMapper;


    public AuthApplicationService(UserMapper userMapper, LoginUseCaseImp loginUseCaseImp, CreateUserUseCaseImp createUserUseCaseImp, RegisterMapper registerMapper, LoginMapper loginMapper) {
        this.userMapper = userMapper;
        this.loginUseCaseImp = loginUseCaseImp;
        this.createUserUseCaseImp = createUserUseCaseImp;
        this.registerMapper = registerMapper;
        this.loginMapper = loginMapper;
    }

    public RegisterResponseDto register(RegisterDto dto) {
        User user = registerMapper.toDomain(dto);
        User salvo = createUserUseCaseImp.execute(user);
        return registerMapper.toResponse(salvo);
    }
    public String login(LoginDto dto) {
        User user = loginMapper.toDomain(dto);
        return loginUseCaseImp.execute(user);
    }
}
