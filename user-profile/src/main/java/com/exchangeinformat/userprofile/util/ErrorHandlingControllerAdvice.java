package com.exchangeinformat.userprofile.util;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        List<Violation> required_parametrs = e.getConstraintViolations().stream()
                .map(violation -> new Violation(violation.getPropertyPath().toString()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(required_parametrs);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Violation> required_parametrs = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(required_parametrs);
    }
}
