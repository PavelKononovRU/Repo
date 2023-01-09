package com.example.subpayment.controllerAdvice;

import com.example.subpayment.util.PaymentStatus;
import com.example.subpayment.util.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class SubPaymentControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<PaymentStatus> catchPaymentException(Exception e, WebRequest webRequest) {
        PaymentStatus paymentStatus = new PaymentStatus(Status.ERROR,
                            "Платеж не прошел, пожалуйста повторите позже");
        return new ResponseEntity<>(paymentStatus, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
