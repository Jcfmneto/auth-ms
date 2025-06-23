package com.example.msauth.adapter.service;

import com.example.msauth.adapter.controllers.dto.DtoMapper;
import com.example.msauth.adapter.controllers.dto.RegisterDto;
import com.example.msauth.adapter.controllers.dto.ResponseDto;
import com.example.msauth.application.usecases.CreateUserUseCaseImp;
import com.example.msauth.application.usecases.LoginUseCaseImp;
import com.example.msauth.domain.entities.User;
import com.example.msauth.infra.gateway.UserGatewayImpl;
import com.example.msauth.infra.persistence.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthApplicationService {

    private final UserMapper userMapper;
    private final LoginUseCaseImp loginUseCaseImp;
    private final CreateUserUseCaseImp createUserUseCaseImp;
    private final DtoMapper dtoMapper;

    public AuthApplicationService(UserMapper userMapper, UserGatewayImpl userGateway, PasswordEncoder passwordEncoder, LoginUseCaseImp loginUseCaseImp, CreateUserUseCaseImp createUserUseCaseImp, DtoMapper dtoMapper) {
        this.userMapper = userMapper;
        this.loginUseCaseImp = loginUseCaseImp;
        this.createUserUseCaseImp = createUserUseCaseImp;
        this.dtoMapper = dtoMapper;
    }

    public ResponseDto register(@Valid RegisterDto dto) {
        User user = dtoMapper.toDomain(dto);
        User salvo = createUserUseCaseImp.execute(user);
        return dtoMapper.toResponse(salvo);

    }
}
