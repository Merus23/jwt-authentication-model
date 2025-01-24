package com.example.jwt_authentication_model.controllers;

import com.example.jwt_authentication_model.dtos.request.LoginRequestDTO;
import com.example.jwt_authentication_model.dtos.request.UserRequestDTO;
import com.example.jwt_authentication_model.dtos.response.LoginResponseDTO;
import com.example.jwt_authentication_model.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(authenticationService.authentication(loginRequest)));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerNewUserAndAuthenticate(userRequestDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
