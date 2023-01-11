package com.example.subpayment.controllerAdvice;

import com.example.subpayment.util.PaymentStatus;
import com.example.subpayment.util.TestPaymentException;
import com.example.subpayment.util.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class SubPaymentControllerAdvice {

    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.UnprocessableEntity.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public PaymentStatus catchPaymentException(HttpClientErrorException.UnprocessableEntity e, WebRequest webRequest) {
        PaymentStatus paymentStatus = new PaymentStatus(Status.ERROR,
                            e.getMessage());
        return paymentStatus;
    }


}
