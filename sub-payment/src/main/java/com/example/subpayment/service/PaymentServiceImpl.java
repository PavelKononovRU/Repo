package com.example.subpayment.service;

import com.example.subpayment.dto.PaymentDTO;
import com.example.subpayment.entity.Payment;
import com.example.subpayment.mappers.StubPaymentMapper;
import com.example.subpayment.repository.StudPaymentRepository;
import com.example.subpayment.util.PaymentStatus;
import com.example.subpayment.util.TestPaymentException;
import com.example.subpayment.util.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentsService {

    private final StudPaymentRepository studPaymentRepository;
    @Autowired
    public PaymentServiceImpl(StudPaymentRepository studPaymentRepository) {
        this.studPaymentRepository = studPaymentRepository;
    }

    @Override
    public Payment getPayment(Long id) {
        return studPaymentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PaymentStatus createPayment(PaymentDTO paymentDTO) throws TestPaymentException {
        int res = (int) ((Math.random() * 2));
        Payment payment = StubPaymentMapper.INSTANCE.toEntity(paymentDTO);
        payment.setCreateAt(new Date());
        if (res >= 1) {
            payment.setStatus(Status.SUCCESSFULLY);
            studPaymentRepository.save(payment);
            return new PaymentStatus(Status.SUCCESSFULLY, "Ваш платеж успешно принят");
        } else {
            payment.setStatus(Status.ERROR);
            studPaymentRepository.save(payment);
            return new PaymentStatus(Status.ERROR,"Ваш платеж не принят, пожалуйста,потворите позже.");
        }
    }

    @Override
    @Transactional
    public PaymentStatus createPaymentTest(Payment payment) {
        Payment paymentUp = new Payment();
        payment.setCreateAt(new Date());
        payment.setStatus(Status.SUCCESSFULLY);
        studPaymentRepository.save(payment);
        return new PaymentStatus(Status.SUCCESSFULLY, "Ваш платеж успешно принят");
    }

}
