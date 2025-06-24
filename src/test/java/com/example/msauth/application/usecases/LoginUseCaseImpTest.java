package com.example.msauth.application.usecases;

import com.example.msauth.domain.entities.User;
import com.example.msauth.domain.exceptions.SenhaAuthInvalida;
import com.example.msauth.domain.exceptions.UserNotFoundException;
import com.example.msauth.domain.gateways.PasswordEncoderGateway;
import com.example.msauth.domain.gateways.TokenGateway;
import com.example.msauth.domain.gateways.UserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginUseCaseImpTest {

    private UserGateway userGateway;
    private TokenGateway tokenGateway;
    private PasswordEncoderGateway passwordEncoderGateway;
    private LoginUseCaseImp loginUseCase;

    @BeforeEach
    void setUp() {
        userGateway = mock(UserGateway.class);
        tokenGateway = mock(TokenGateway.class);
        passwordEncoderGateway = mock(PasswordEncoderGateway.class);
        loginUseCase = new LoginUseCaseImp(userGateway, tokenGateway, passwordEncoderGateway);
    }

    @Test
    void deveAutenticarComSucesso() {
        User request = new User("email@email.com", "senhaCorreta");
        User usuarioSalvo = new User("email@email.com", "senhaHashada");

        when(userGateway.findByEmail(request.getEmail())).thenReturn(Optional.of(usuarioSalvo));
        when(passwordEncoderGateway.matches(request.getPassword(), usuarioSalvo.getPassword())).thenReturn(true);
        when(tokenGateway.generateToken(usuarioSalvo.getEmail())).thenReturn("fakeToken");

        String token = loginUseCase.execute(request);

        assertEquals("fakeToken", token);
        verify(tokenGateway).generateToken(usuarioSalvo.getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoSenhaIncorreta() {
        User request = new User("email@email.com", "senhaErrada");
        User usuarioSalvo = new User("email@email.com", "senhaHashada");

        when(userGateway.findByEmail(request.getEmail())).thenReturn(Optional.of(usuarioSalvo));
        when(passwordEncoderGateway.matches(request.getPassword(), usuarioSalvo.getPassword())).thenReturn(false);

        Exception exception = assertThrows(SenhaAuthInvalida.class, () -> loginUseCase.execute(request));
        assertEquals("Senha invalida", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        User request = new User("naoexiste@email.com", "senha");

        when(userGateway.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> loginUseCase.execute(request));
        assertTrue(exception.getMessage().contains(request.getEmail()));
    }
}
