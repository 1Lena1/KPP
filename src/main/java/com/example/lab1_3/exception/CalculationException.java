package com.example.lab1_3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong parameters")
public class CalculationException extends RuntimeException {
    public CalculationException(String message) {
        super(message);
    }
}
