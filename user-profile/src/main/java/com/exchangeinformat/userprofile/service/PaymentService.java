package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Payment;
import com.exchangeinformat.userprofile.repository.PaymentRepository;

import java.util.List;

public interface PaymentService {
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();

    void createPayment(Payment payment);

    void updatePayment(Payment payment);

    void deletePaymentById(Long id);
}
