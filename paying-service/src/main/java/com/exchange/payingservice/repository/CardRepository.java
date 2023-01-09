package com.exchange.payingservice.repository;

import com.exchange.payingservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card getCardByNumber(String number);

}
