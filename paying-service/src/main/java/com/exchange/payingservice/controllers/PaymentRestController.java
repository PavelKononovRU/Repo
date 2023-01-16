package com.exchange.payingservice.controllers;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.dto.StudPaymentDTO;
import com.exchange.payingservice.mappers.PaymentsMapper;
import com.exchange.payingservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
        PaymentDTO paymentDTO = PaymentsMapper.INSTANCE.toDTO(paymentService.getPaymentById(id));
        return ResponseEntity.ok(paymentDTO);
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
    public ResponseEntity<Object> createPayment(@Valid @RequestBody StudPaymentDTO studPayment) {
        return paymentService.methodGetBodyToStudPayment(studPayment);
    }

}