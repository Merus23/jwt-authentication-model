package com.example.jwt_authentication_model.controllers;

import com.example.jwt_authentication_model.dtos.request.LoginRequestDTO;
import com.example.jwt_authentication_model.dtos.request.UserRequestDTO;
import com.example.jwt_authentication_model.dtos.response.LoginResponseDTO;
import com.example.jwt_authentication_model.infra.security.TokenService;
import com.example.jwt_authentication_model.models.User;
import com.example.jwt_authentication_model.models.UserPermission;
import com.example.jwt_authentication_model.repositoties.UserPermissionsRepository;
import com.example.jwt_authentication_model.repositoties.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPermissionsRepository userPermissionsRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequest) throws Exception {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.name(), loginRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequestDTO userRequestDTO) {

        if(this.userRepository.findByEmail(userRequestDTO.email()).isPresent()) return ResponseEntity.badRequest().body("Email already registered");

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequestDTO.password());
        UserPermission permission = this.userPermissionsRepository.findByName(userRequestDTO.permission()).orElseThrow(()-> new RuntimeException("Permission not found"));

        User newUser = new User(userRequestDTO.name(), userRequestDTO.email(), encryptedPassword, permission);
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
