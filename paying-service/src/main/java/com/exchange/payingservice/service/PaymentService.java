package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.dto.StudPaymentDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface PaymentService {

    Payment getPaymentById(Long id);

    List<Payment> getAllPayment();

    void createPayment(PaymentDTO payment);

    void updatePayment(Long id, PaymentDTO paymentDTO);

    void deletePaymentById(Long id);

    StudPaymentDTO testMethodPostToStudPayment();

    ResponseEntity<Object> methodGetBodyToStudPayment(StudPaymentDTO studPayment);
}
