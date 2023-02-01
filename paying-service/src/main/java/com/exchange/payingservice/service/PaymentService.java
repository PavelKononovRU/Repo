package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StubPaymentDTO;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.util.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {

    Payment getPaymentById(Long id);

    List<Payment> getAllPayment();

    StubPaymentDTO createPayment(StubPaymentDTO payment, Status status);

    void updatePayment(Long id, PaymentDTO paymentDTO);

    void deletePaymentById(Long id);

    ResponseEntity<Object> methodGetBodyToStubPayment(StubPaymentDTO studPayment);
}

