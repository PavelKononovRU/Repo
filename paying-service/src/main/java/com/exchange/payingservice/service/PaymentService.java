package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StubPaymentDTO;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.util.PaymentStatus;
import com.exchange.payingservice.util.Status;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface PaymentService {

    Payment getPaymentById(Long id);

    List<PaymentDTO> getAllPayment();

    StubPaymentDTO createPayment(StubPaymentDTO stubPaymentDTO, Status status);

    void updatePayment(Long id, PaymentDTO paymentDTO);

    void deletePaymentById(Long id);

    ResponseEntity<PaymentStatus> methodGetBodyToStubPayment(StubPaymentDTO studPayment, Principal principal);
}

