package com.example.jwt_authentication_model.exceptions.custom;

/**
 *
 * */
public class ConflictErrorException extends RuntimeException {
    public ConflictErrorException(String message) {
        super(message);
    }
}
