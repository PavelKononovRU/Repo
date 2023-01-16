package com.example.subpayment.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TestPaymentException extends Exception {

    public TestPaymentException(String message) {
        super(message);
    }

}