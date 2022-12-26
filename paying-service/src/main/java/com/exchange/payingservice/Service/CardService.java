package com.exchange.payingservice.Service;

import com.exchange.payingservice.model.Card;

import java.util.List;

public interface CardService {

    void createCard(Card card);

    void updateCard(Card card);

    void deleteCard(Long id);

    List<Card> getAllCard();

    Card getCardById(Long id);

}
