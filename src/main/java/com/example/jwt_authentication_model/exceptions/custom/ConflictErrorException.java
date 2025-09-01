package com.example.jwt_authentication_model.exceptions.custom;

public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message) {
        super(message);
    }
}
