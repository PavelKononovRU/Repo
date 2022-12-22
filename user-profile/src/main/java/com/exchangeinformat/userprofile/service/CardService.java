package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Card;

import java.util.List;

public interface CardService {
    List<Card> getAllCards();
    Card getCardById(Long id);
    void createCard(Card card);
    void updateCard(Card card);
    void deleteCard(Long id);
}
