package com.example.subpayment.service;

import com.example.subpayment.dto.PaymentDTO;
import com.example.subpayment.entity.Payment;
import com.example.subpayment.util.PaymentStatus;
import com.example.subpayment.util.TestPaymentException;

public interface PaymentsService {

    Payment getPayment(Long id);

    PaymentStatus createPayment(PaymentDTO payment) throws TestPaymentException;

    PaymentStatus createPaymentTest(Payment payment);

}
