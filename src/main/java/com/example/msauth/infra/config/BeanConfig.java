package com.example.msauth.infra.config;

import com.example.msauth.domain.gateways.TokenGateway;

import com.example.msauth.infra.gateway.PasswordEncoderGatewayImpl;
import com.example.msauth.infra.gateway.TokenGatewayImpl;
import com.example.msauth.infra.gateway.UserGatewayImpl;
import com.example.msauth.infra.persistence.mapper.UserMapper;
import com.example.msauth.infra.persistence.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    PasswordEncoderGatewayImpl passwordEncoderGateway(PasswordEncoder passwordEncoder) {
        return new PasswordEncoderGatewayImpl(passwordEncoder);
    }

    @Bean
    UserGatewayImpl userGateway(UserRepository userRepository, UserMapper userMapper) {
        return new UserGatewayImpl(userRepository, userMapper);
    }

}
