package com.exchange.payingservice.repository;

import com.exchange.payingservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card getCardByNumber(String number);

}
