package com.example.lab1_3.errorlogger;

import com.example.lab1_3.exception.CalculationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice
public class LogErrors extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CalculationException.class)
    public ResponseEntity<Object> handleException(@NotNull CalculationException e) {
        logger.error("ERROR CODE 400", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(@NotNull Exception e) {
        logger.error("ERROR CODE 500", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}