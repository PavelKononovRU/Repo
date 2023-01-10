package com.exchangeinformant.subscription.exception;

import com.exchangeinformant.subscription.util.error.SubscriptionError;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public ResponseEntity<SubscriptionError> catchResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        SubscriptionError message = new SubscriptionError("Подписка не найдена", HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<SubscriptionError> catchResourceNotFoundException(EmptyResultDataAccessException e, WebRequest request) {
        SubscriptionError message = new SubscriptionError("Невозможно удалить подписку", HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<SubscriptionError> catchResourceNotFoundException(IndexOutOfBoundsException e, WebRequest request) {
        SubscriptionError message = new SubscriptionError("Неверное значение параметра 'limit'", "Попытка получить такое кол-во элементов, которое превышает размер страницы: " + e.getMessage(), request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<SubscriptionError> catchServerErrorException(ServerErrorException e, WebRequest request) {
        SubscriptionError message = new SubscriptionError("Непредвиденная ошибка сервера", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Серверу не удалось корректно обработать запрос: " + e.getMessage(), request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler()
    public ResponseEntity<SubscriptionError> catchUnprocessableEntityException(UnprocessableEntityException e, WebRequest request) {
        SubscriptionError message = new SubscriptionError("Невозможно обработать объект", HttpStatus.UNPROCESSABLE_ENTITY.value(), "Подробнее: " + e.getMessage(), request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}