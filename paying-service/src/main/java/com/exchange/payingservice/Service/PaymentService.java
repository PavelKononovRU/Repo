package com.exchange.payingservice.Service;

import com.exchange.payingservice.model.Payment;

import java.util.List;

public interface PaymentService {

    Payment getPaymentById(Long id);

    List<Payment> getAllPayment();

    void createPayment(Payment payment);

    void updatePayment(Payment payment);

    void deletePaymentById(Long id);

}
