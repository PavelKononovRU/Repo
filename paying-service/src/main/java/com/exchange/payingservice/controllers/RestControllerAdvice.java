package com.exchange.payingservice.controllers;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StatusCards;
import com.exchange.payingservice.exceptions.PaymentException;
import com.exchange.payingservice.util.PaymentStatus;
import com.exchange.payingservice.util.Status;
import com.exchange.payingservice.util.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestControllerAdvice {

    public static ResponseEntity<Object> generateResponsePost(CardDTO card) {
        Map<String, Object> map = new HashMap<>();
        map.put("date", new StatusCards(String.format("Карта с номером %s сохранена", card.getNumber()),
                "Управляйте картами в платежной информации"));
        map.put("update_at", LocalDateTime.now());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", message);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(CardDTO cardDTO) {
        if (cardDTO == null) {
            throw new UsernameNotFoundException("Card not found");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("date", cardDTO);
        map.put("create_at", LocalDateTime.now());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> generateResponsePut() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", new StatusCards("Карта изменена",
                "Управляйте картами в платежной информации"));
        map.put("create_at", LocalDateTime.now());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> generateResponseDelete(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("date", new StatusCards(String.format("Карта c id %d удалена", id),
                "Управляйте картами в платежной информации"));
        map.put("delete_at", LocalDateTime.now());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> generatePaymentsResponse(PaymentDTO paymentDTO) {
        if (paymentDTO == null) {
            throw new UsernameNotFoundException("Payment not found");
        }
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }

    //Перехватывает исключения валидации
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(MethodArgumentNotValidException e) {
        final List<StatusCards> violations = e.getAllErrors().stream()
                .map(
                        status -> new StatusCards(
                                status.getObjectName(),
                                status.getDefaultMessage()
                        )
                )
                .collect(Collectors.toList());

        return new ValidationErrorResponse(violations);
    }

    @ResponseBody
    @ExceptionHandler({HttpClientErrorException.UnprocessableEntity.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public PaymentStatus catchPaymentException(HttpClientErrorException.UnprocessableEntity e) {
        return new PaymentStatus(Status.ERROR,"Платеж не прошел,пожалуйста,повторите позже.");
    }

    @ResponseBody
    @ExceptionHandler({PaymentException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public PaymentStatus catchPaymentException(PaymentException e) {
        return new PaymentStatus(Status.ERROR,e.getMessage());
    }
}
