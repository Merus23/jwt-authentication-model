package com.example.jwt_authentication_model.dtos.User;

import java.util.Optional;

public record UserRequestDTO(Optional<Long> id,String name, String email, String password, String permission) { }
