package com.example.jwt_authentication_model.exceptions;

import com.example.jwt_authentication_model.exceptions.custom.BadRequestException;
import com.example.jwt_authentication_model.exceptions.custom.ConflictErrorException;
import com.example.jwt_authentication_model.exceptions.custom.NotFoundContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictErrorException.class)
    private ResponseEntity<RestErrorMessage> conflict(ConflictErrorException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestErrorMessage(exception.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now()));
    }

    @ExceptionHandler(NotFoundContentException.class)
    private ResponseEntity<RestErrorMessage> notFoundContent(NotFoundContentException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()));
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<RestErrorMessage> badRequest(BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()));
    }


}
