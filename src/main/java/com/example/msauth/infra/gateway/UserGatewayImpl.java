package com.example.msauth.infra.gateway;

import com.example.msauth.domain.entities.User;
import com.example.msauth.domain.gateways.UserGateway;
import com.example.msauth.infra.persistence.entity.UserEntity;
import com.example.msauth.infra.persistence.mapper.UserMapper;
import com.example.msauth.infra.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserGatewayImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userRepository.save(userEntity);
        return userMapper.toDomain(userEntity);
    }
    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDomain);
    }
}
