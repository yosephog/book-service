package com.polar.bookService.controllers;

import com.polar.bookService.exception.InventoryAlreadyExistException;
import com.polar.bookService.exception.InventoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class InventoryControllerAdvice {

    @ExceptionHandler(InventoryAlreadyExistException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String inventoryAlreadyExistExceptionHandler(InventoryAlreadyExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String inventoryNotFoundExceptionHandler(InventoryNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldError = ((FieldError)error).getField();
                    var errorMessage = ex.getMessage();
                    errors.put(fieldError, errorMessage);
                });
        return errors;
    }
}
