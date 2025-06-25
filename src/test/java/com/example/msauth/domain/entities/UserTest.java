package com.example.msauth.domain.entities;

import com.example.msauth.domain.exceptions.EmailInvalidoException;
import com.example.msauth.domain.exceptions.SenhaInvalidaException;
import com.example.msauth.domain.exceptions.UsernameInvalidoException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @Test
    void deveCriarUsuarioValido() {
        User user = new User("julio", "Senha@123", "julio@email.com", 4L);
        assertNotNull(user);
        assertEquals("julio", user.getUsername());
    }

    @Test
    void deveLancarExcecaoParaUsernameInvalido() {
        Exception exception = assertThrows(UsernameInvalidoException.class, () ->
                new User("", "Senha@123", "julio@email.com", 3L));
        assertEquals("Username não pode ser vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailInvalido() {
        Exception exception = assertThrows(EmailInvalidoException.class, () ->
                new User("julio", "Senha@123", "email_invalido", 1L)
        );
        assertTrue(exception.getMessage().contains("Formato de email inválido"));
    }

    @Test
    void deveLancarExcecaoParaSenhaInvalida() {
        Exception exception = assertThrows(SenhaInvalidaException.class, () ->
                new User("julio", "123", "julio@email.com", 1L)
        );
        assertTrue(exception.getMessage().contains("Senha inválida"));
    }


}