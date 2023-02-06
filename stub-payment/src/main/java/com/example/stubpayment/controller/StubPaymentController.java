package com.example.stubpayment.controller;

import com.example.stubpayment.dto.PaymentDTO;
import com.example.stubpayment.service.PaymentsService;
import com.example.stubpayment.util.PaymentStatus;
import com.example.stubpayment.util.enums.Status;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stub/payment")
public class StubPaymentController {

    private final PaymentsService paymentsService;

    public StubPaymentController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }


    @SneakyThrows
    @PostMapping
    public ResponseEntity<PaymentStatus> createStubPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentStatus paymentStatus = paymentsService.createPayment(paymentDTO);
        return new ResponseEntity<>(paymentStatus, paymentStatus.getStatus().equals(Status.SUCCESSFULLY) ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
