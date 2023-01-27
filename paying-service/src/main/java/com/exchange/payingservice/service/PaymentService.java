package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StudPaymentDTO;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.util.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {

    Payment getPaymentById(Long id);

    List<Payment> getAllPayment();

    StudPaymentDTO createPayment(StudPaymentDTO payment, Status status);

    void updatePayment(Long id, PaymentDTO paymentDTO);

    void deletePaymentById(Long id);

    ResponseEntity<Object> methodGetBodyToStudPayment(StudPaymentDTO studPayment);
}
