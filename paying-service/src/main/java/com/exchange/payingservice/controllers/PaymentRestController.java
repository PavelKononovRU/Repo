package com.exchange.payingservice.controllers;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StubPaymentDTO;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.mappers.PaymentsMapper;
import com.exchange.payingservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(("/{id}"))
    public ResponseEntity<Object> getPayment(@PathVariable Long id) {
        PaymentDTO paymentDTO = PaymentsMapper.INSTANCE.toDTO(paymentService.getPaymentById(id));
        return RestControllerAdvice.generatePaymentsResponse(paymentDTO);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayment());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePayment(@PathVariable("id") Long id, @Valid @RequestBody PaymentDTO paymentDTO) {
        paymentService.updatePayment(id, paymentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createPayment(@Valid @RequestBody StubPaymentDTO stubPayment) {
        return paymentService.methodGetBodyToStubPayment(stubPayment);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return RestControllerAdvice.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}