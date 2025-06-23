package com.example.msauth.domain.gateways;

import com.example.msauth.domain.entities.User;

import java.util.Optional;

public interface UserGateway {

    User save(User user);

    Optional<User> findByEmail(String email);
}
