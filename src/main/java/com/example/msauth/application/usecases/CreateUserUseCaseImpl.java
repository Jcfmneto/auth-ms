package com.example.msauth.application.usecases;

import com.example.msauth.domain.entities.User;
import com.example.msauth.domain.entities.UserFactory;
import com.example.msauth.domain.gateways.PasswordEncoderGateway;
import com.example.msauth.domain.gateways.UserGateway;

public class CreateUserUseCaseImpl implements  CreateUserUseCase {

    private final PasswordEncoderGateway passwordEncoder;

    private final UserGateway userGateway;

    public CreateUserUseCaseImpl(PasswordEncoderGateway passwordEncoder, UserGateway userGateway) {
        this.passwordEncoder = passwordEncoder;
        this.userGateway = userGateway;
    }

    @Override
    public User execute(User userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User user = UserFactory.createWithEncryptedPassword(userRequest, encodedPassword);
        return userGateway.save(user);
    }

}
