package com.example.subpayment.service;

import com.example.subpayment.entity.Payment;
import com.example.subpayment.util.PaymentStatus;
import com.example.subpayment.util.TestPaymentException;

public interface PaymentsService {

    Payment getPayment(Long id);

    PaymentStatus createPayment(Payment payment) throws TestPaymentException;

    PaymentStatus createPaymentTest(Payment payment);

}
