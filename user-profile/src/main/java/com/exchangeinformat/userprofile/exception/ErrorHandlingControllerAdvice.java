package com.exchangeinformat.userprofile.exception;

import com.exchangeinformat.userprofile.util.Data;
import com.exchangeinformat.userprofile.util.ValidationResponse;
import com.exchangeinformat.userprofile.util.Violation;
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
    public ValidationResponse onConstraintValidationException(ConstraintViolationException e) {
        List<Violation> required_parameters = e.getConstraintViolations().stream()
                .map(violation -> new Violation(violation.getPropertyPath().toString()))
                .collect(Collectors.toList());
        Data data = new Data("Данные не прошли валидацию", required_parameters);

        return new ValidationResponse(data);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ValidationResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Violation> required_parameters = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField()))
                .collect(Collectors.toList());
        Data data = new Data("Данные не прошли валидацию", required_parameters);

        return new ValidationResponse(data);
    }
}
