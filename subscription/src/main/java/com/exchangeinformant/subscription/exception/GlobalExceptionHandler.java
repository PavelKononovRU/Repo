package com.exchangeinformant.subscription.exception;

import com.exchangeinformant.subscription.util.error.SubscriptionError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler()
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<SubscriptionError> catchResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        SubscriptionError message = new SubscriptionError("Запрашиваемый ресурс не найден", HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<SubscriptionError> catchServerErrorException(Exception e, WebRequest request) {
        SubscriptionError message = new SubscriptionError("Непредвиденная ошибка сервера", HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler()
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<SubscriptionError> catchUnprocessableEntityException(UnprocessableEntityException e, WebRequest request) {
        SubscriptionError message = new SubscriptionError("Невозможно обработать объект", HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}