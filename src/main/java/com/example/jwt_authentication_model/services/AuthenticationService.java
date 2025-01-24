package com.example.jwt_authentication_model.services;

import com.example.jwt_authentication_model.dtos.request.LoginRequestDTO;
import com.example.jwt_authentication_model.infra.security.TokenService;
import com.example.jwt_authentication_model.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String authentication(LoginRequestDTO loginRequest) throws Exception {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        return this.tokenService.generateToken((User) auth.getPrincipal());
    }

}
