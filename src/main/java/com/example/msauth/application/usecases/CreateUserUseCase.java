package com.example.msauth.application.usecases;

import com.example.msauth.domain.entities.User;

public interface CreateUserUseCase {
    User execute(User user);

}
