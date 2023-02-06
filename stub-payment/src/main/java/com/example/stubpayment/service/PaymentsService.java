package com.example.stubpayment.service;

import com.example.stubpayment.dto.PaymentDTO;
import com.example.stubpayment.entity.Payment;
import com.example.stubpayment.util.PaymentStatus;
import com.example.stubpayment.util.TestPaymentException;

public interface PaymentsService {

    Payment getPayment(Long id);

    PaymentStatus createPayment(PaymentDTO payment) throws TestPaymentException;


}
