package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
