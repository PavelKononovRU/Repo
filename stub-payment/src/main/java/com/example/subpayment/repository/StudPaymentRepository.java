package com.example.subpayment.repository;

import com.example.subpayment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudPaymentRepository extends JpaRepository<Payment, Long> {
}
