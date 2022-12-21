package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Payment;
import com.exchangeinformat.userprofile.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepo;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void createPayment(Payment payment) {
        paymentRepo.save(payment);
    }

    @Override
    @Transactional
    public void updatePayment(Payment payment) {
        paymentRepo.save(payment);
    }

    @Override
    @Transactional
    public void deletePaymentById(Long id) {
        paymentRepo.deleteById(id);
    }
}
