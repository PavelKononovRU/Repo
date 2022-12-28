package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CardService {

    CardDTO createCard(Card card);

    void updateCard(Long id,Card card);

    void deleteCard(Long id);

    List<Card> getAllCard();

    Card getCardById(Long id);


}
