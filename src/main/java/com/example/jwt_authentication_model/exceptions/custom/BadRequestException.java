package com.example.jwt_authentication_model.exceptions.custom;


/**
 * Should be thrown when the required resource is bad formatted or missing parameters.
 * @Author Mateus Silva
 * */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
