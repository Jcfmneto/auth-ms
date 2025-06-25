package com.example.msauth.infra.config;

import com.example.msauth.application.usecases.CreateUserUseCaseImpl;
import com.example.msauth.application.usecases.LoginUseCaseImpl;
import com.example.msauth.domain.gateways.PasswordEncoderGateway;
import com.example.msauth.domain.gateways.TokenGateway;

import com.example.msauth.domain.gateways.UserGateway;
import com.example.msauth.infra.gateway.PasswordEncoderGatewayImpl;
import com.example.msauth.infra.gateway.TokenGatewayImpl;
import com.example.msauth.infra.gateway.UserGatewayImpl;
import com.example.msauth.infra.persistence.mapper.UserMapper;
import com.example.msauth.infra.persistence.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {


    @Bean
    UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    TokenGateway tokenGateway() {
        return new TokenGatewayImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    PasswordEncoderGateway  passwordEncoderGateway(PasswordEncoder passwordEncoder) {
        return  new PasswordEncoderGatewayImpl(passwordEncoder);
    }

    @Bean
    UserGatewayImpl userGateway(UserRepository userRepository, UserMapper userMapper) {
        return new UserGatewayImpl(userRepository, userMapper);
    }

    @Bean
    LoginUseCaseImpl loginUseCaseImp(UserGateway userGateway, TokenGateway tokenGateway, PasswordEncoderGateway passwordEncoder) {
        return new LoginUseCaseImpl(userGateway, tokenGateway, passwordEncoder);
    }

    @Bean
    CreateUserUseCaseImpl createUserUseCaseImp(PasswordEncoderGateway passwordEncoderGateway, UserGateway userGateway) {
        return new CreateUserUseCaseImpl(passwordEncoderGateway, userGateway);
    }


}
