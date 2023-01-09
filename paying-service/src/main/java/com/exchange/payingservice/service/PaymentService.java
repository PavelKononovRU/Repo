package com.exchange.payingservice.service;

import com.exchange.payingservice.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment getPaymentById(Long id);

    List<Payment> getAllPayment();

    void createPayment(Payment payment);

    void updatePayment(Payment payment);

    void deletePaymentById(Long id);

}
