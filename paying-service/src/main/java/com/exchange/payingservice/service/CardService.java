package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;

import java.util.List;

public interface CardService {

    void createCard(CardDTO card);

    void updateCard(Long id, CardDTO cardDTO);

    void deleteCard(Long id);

    List<CardDTO> getAllCard();

     Card getCardById(Long id);
}
