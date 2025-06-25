package com.example.msauth.application.usecases;
import com.example.msauth.domain.entities.User;
import com.example.msauth.domain.exceptions.SenhaAuthInvalida;
import com.example.msauth.domain.exceptions.UserNotFoundException;
import com.example.msauth.domain.gateways.PasswordEncoderGateway;
import com.example.msauth.domain.gateways.TokenGateway;
import com.example.msauth.domain.gateways.UserGateway;


public class LoginUseCaseImpl implements LoginUseCase {

    private final UserGateway userGateway;
    private final TokenGateway tokenGateway;
    private final PasswordEncoderGateway passwordEncoderGateway;

    public LoginUseCaseImpl(UserGateway userGateway, TokenGateway tokenGateway, PasswordEncoderGateway passwordEncoderGateway) {
        this.userGateway  = userGateway;
        this.tokenGateway = tokenGateway;
        this.passwordEncoderGateway = passwordEncoderGateway;
    }

    @Override
    public String execute(User userRequest) {
        User user = userGateway.findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException(userRequest.getEmail()));
        
       if(!passwordEncoderGateway.matches(userRequest.getPassword(), user.getPassword())){
           throw new SenhaAuthInvalida("Senha invalida");
       }
       
        return tokenGateway.generateToken(user.getEmail()); 
    } 
}