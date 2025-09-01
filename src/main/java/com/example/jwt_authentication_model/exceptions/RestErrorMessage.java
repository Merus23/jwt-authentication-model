package com.example.jwt_authentication_model.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public record RestErrorMessage(String Message, HttpStatus status, LocalDateTime time) {
}
