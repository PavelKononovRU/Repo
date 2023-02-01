package com.example.stubpayment.service;

import com.example.stubpayment.dto.PaymentDTO;
import com.example.stubpayment.entity.Payment;
import com.example.stubpayment.mappers.StubPaymentMapper;
import com.example.stubpayment.repository.StubPaymentRepository;
import com.example.stubpayment.util.PaymentStatus;
import com.example.stubpayment.util.TestPaymentException;
import com.example.stubpayment.util.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentsService {

    private final StubPaymentRepository stubPaymentRepository;
    @Autowired
    public PaymentServiceImpl(StubPaymentRepository stubPaymentRepository) {
        this.stubPaymentRepository = stubPaymentRepository;
    }

    @Override
    public Payment getPayment(Long id) {
        return stubPaymentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PaymentStatus createPayment(PaymentDTO paymentDTO) throws TestPaymentException {
        int res = (int) ((Math.random() * 2));
        Payment payment = StubPaymentMapper.INSTANCE.toEntity(paymentDTO);
        payment.setCreateAt(new Date());
        if (res >= 1) {
            payment.setStatus(Status.SUCCESSFULLY);
            stubPaymentRepository.save(payment);
            return new PaymentStatus(Status.SUCCESSFULLY, "Ваш платеж успешно принят");
        } else {
            payment.setStatus(Status.ERROR);
            stubPaymentRepository.save(payment);
            throw new TestPaymentException("Ваш платеж не прошел,пожалуйста,повторите позже.");
        }
    }

}
