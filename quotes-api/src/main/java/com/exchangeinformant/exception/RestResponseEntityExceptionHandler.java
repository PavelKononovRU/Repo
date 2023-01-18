package com.exchangeinformant.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:37
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ QuotesException.class })
    public ResponseEntity<ExceptionBody> handleQuotesException(QuotesException e) {
        return new ResponseEntity<>(
                new ExceptionBody(ErrorCodes.valueOf(e.getMessage()).getErrorMessage(), ErrorCodes.valueOf(e.getMessage()).getErrorCode()), new HttpHeaders(), HttpStatus.BAD_GATEWAY);
    }

}
