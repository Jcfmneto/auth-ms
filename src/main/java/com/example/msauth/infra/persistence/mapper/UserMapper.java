package com.example.msauth.infra.persistence.mapper;

import com.example.msauth.domain.entities.User;
import com.example.msauth.infra.persistence.entity.UserEntity;

public class UserMapper {

    public User toDomain(UserEntity user){
        return new User(user.getUsername(), user.getPassword(), user.getEmail());
    }

    public UserEntity toEntity(User userDomain){
        return new UserEntity(userDomain.getUsername(), userDomain.getPassword(), userDomain.getEmail(), userDomain.getCreatedAt());
    }
}
