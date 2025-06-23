package com.example.msauth.application.usecases;

import com.example.msauth.domain.entities.User;

public interface LoginUseCase {
    String execute(User userRequest);
}
