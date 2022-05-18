package com.example.lab1_3.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong parameters")
public class CalculationException extends RuntimeException {
    public CalculationException(String message) {
        super(message);
    }
}
