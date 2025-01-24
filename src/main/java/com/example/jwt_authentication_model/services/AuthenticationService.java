package com.example.jwt_authentication_model.services;

import com.example.jwt_authentication_model.dtos.request.LoginRequestDTO;
import com.example.jwt_authentication_model.dtos.request.UserRequestDTO;
import com.example.jwt_authentication_model.infra.security.TokenService;
import com.example.jwt_authentication_model.models.User;
import com.example.jwt_authentication_model.models.UserPermission;
import com.example.jwt_authentication_model.repositoties.UserPermissionsRepository;
import com.example.jwt_authentication_model.repositoties.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserPermissionsRepository userPermissionsRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthenticationService(UserPermissionsRepository userPermissionsRepository, AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
        this.userPermissionsRepository = userPermissionsRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public String authentication(LoginRequestDTO loginRequest) throws Exception {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        return this.tokenService.generateToken((User) auth.getPrincipal());
    }

    public String registerNewUserAndAuthente(UserRequestDTO userRequestDTO) throws Exception {
        if(this.userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequestDTO.password());
        UserPermission permission = this.userPermissionsRepository.findByName(userRequestDTO.permission()).orElseThrow(()-> new RuntimeException("Permission not found"));

        User newUser = new User(userRequestDTO.name(), userRequestDTO.email(), encryptedPassword, permission);
        this.userRepository.save(newUser);

        return this.authentication(new LoginRequestDTO(userRequestDTO.email(), userRequestDTO.password()));
    }

}
