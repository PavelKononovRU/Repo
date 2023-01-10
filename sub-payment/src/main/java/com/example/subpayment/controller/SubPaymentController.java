package com.example.subpayment.controller;

import com.example.subpayment.dto.PaymentDTO;
import com.example.subpayment.entity.Payment;
import com.example.subpayment.service.PaymentsService;
import com.example.subpayment.util.PaymentStatus;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stud/payment")
public class SubPaymentController {

    private final PaymentsService paymentsService;

    public SubPaymentController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }


    @SneakyThrows
    @PostMapping("/v1")
    public ResponseEntity<PaymentStatus> testV22Method(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setExt_id(paymentDTO.getExt_id());
        PaymentStatus paymentStatus = paymentsService.createPayment(payment);
        return new ResponseEntity<>(paymentStatus, HttpStatus.OK);
    }

}
