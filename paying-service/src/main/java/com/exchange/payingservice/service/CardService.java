package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.StatusCards;
import com.exchange.payingservice.entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CardService {

    StatusCards createCard(Card card);

    void updateCard(Card card);

    void deleteCard(Long id);

    List<Card> getAllCard();

    Card getCardById(Long id);


}
