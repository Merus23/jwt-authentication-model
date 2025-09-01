package com.example.jwt_authentication_model.exceptions.custom;

/**
 * Throw this Exception when a resource was not found
 * @Author Mateus Silva
 * */
public class NotFoundContentException extends RuntimeException {
    public NotFoundContentException(String message) {
        super(message);
    }
}
