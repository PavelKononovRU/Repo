package com.exchange.payingservice.controllers;

import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.service.PaymentService;
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
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayment());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createPayment(@RequestBody Payment payment) {
        paymentService.createPayment(payment);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updatePayment(@RequestBody Payment payment) {
        paymentService.updatePayment(payment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}