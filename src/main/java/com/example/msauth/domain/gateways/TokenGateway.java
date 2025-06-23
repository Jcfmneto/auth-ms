package com.example.msauth.domain.gateways;

public interface TokenGateway {

    String generateToken(String email);
}
