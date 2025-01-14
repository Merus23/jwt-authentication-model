package com.example.jwt_authentication_model.dtos.User;

public record UserRequestDTO(String name, String email, String password, String permission) { }
