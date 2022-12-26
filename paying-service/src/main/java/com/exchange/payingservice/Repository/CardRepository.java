package com.exchange.payingservice.Repository;

import com.exchange.payingservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
