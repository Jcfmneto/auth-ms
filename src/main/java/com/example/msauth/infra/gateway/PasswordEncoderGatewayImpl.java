package com.example.msauth.infra.gateway;

import com.example.msauth.domain.gateways.PasswordEncoderGateway;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGatewayImpl implements PasswordEncoderGateway {

    private final PasswordEncoder passwordEncoder;
    public PasswordEncoderGatewayImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String requestPassword, String userPassword) {
        return passwordEncoder.matches(requestPassword, userPassword);
    }
}
