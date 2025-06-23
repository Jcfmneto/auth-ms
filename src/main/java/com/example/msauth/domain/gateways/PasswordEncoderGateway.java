package com.example.msauth.domain.gateways;

import com.example.msauth.domain.entities.User;

public interface PasswordEncoderGateway {

    String encode(String password);

    boolean matches(String requestPassword, String userPassword);
}
