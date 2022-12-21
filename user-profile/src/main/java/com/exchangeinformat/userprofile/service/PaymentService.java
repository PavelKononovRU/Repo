package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Payment;
import com.exchangeinformat.userprofile.repository.PaymentRepository;

public interface PaymentService {
    Payment getPaymentById(Long id);

    void createPayment(Payment payment);

    void updatePayment(Payment payment);

    void deletePaymentById(Long id);
}
