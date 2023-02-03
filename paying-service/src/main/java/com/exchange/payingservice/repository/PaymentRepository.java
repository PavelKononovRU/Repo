package com.exchange.payingservice.repository;

import com.exchange.payingservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Modifying
    @Transactional
    @Query(value = "drop sequence if exists ext_id_val;\n" +
            "CREATE SEQUENCE ext_id_val  AS  int  START  WITH  1  INCREMENT  BY  1;",
    nativeQuery = true)
    void getNewSequence();

    @Query(value = "SELECT nextval('ext_id_val')",
    nativeQuery = true)
    int getNextValue();
}
