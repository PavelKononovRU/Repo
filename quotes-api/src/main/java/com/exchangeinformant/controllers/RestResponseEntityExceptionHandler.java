package com.exchangeinformant.controllers;

import com.exchangeinformant.util.ErrorCodes;
import com.exchangeinformant.util.QuotesUpdateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:37
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ QuotesUpdateException.class })
    public ResponseEntity<Test> handleQuotesException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new Test("Error on update the stocks", ErrorCodes.valueOf(ex.getMessage())), new HttpHeaders(), HttpStatus.BAD_GATEWAY);
    }

    @Data
    @AllArgsConstructor
    private static class Test {
        private String message;

        private ErrorCodes errorCode;

    }
}
