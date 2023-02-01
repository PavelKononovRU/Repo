package com.example.stubpayment.repository;

import com.example.stubpayment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StubPaymentRepository extends JpaRepository<Payment, Long> {
}
