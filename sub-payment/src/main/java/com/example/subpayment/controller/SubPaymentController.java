package com.example.subpayment.controller;

import com.example.subpayment.dto.PaymentDTO;
import com.example.subpayment.entity.Payment;
import com.example.subpayment.service.PaymentsService;
import com.example.subpayment.util.PaymentStatus;
import com.example.subpayment.util.enums.Status;
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
    @PostMapping
    public ResponseEntity<PaymentStatus> createStubPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentStatus paymentStatus = paymentsService.createPayment(paymentDTO);
        return new ResponseEntity<>(paymentStatus, paymentStatus.getStatus().equals(Status.SUCCESSFULLY) ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
