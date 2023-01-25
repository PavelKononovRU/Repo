package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

public interface CardService {

    CardDTO createCard(CardDTO card);

    void updateCard(Long id,CardDTO cardDTO);

    void deleteCard(Long id);

    List<Card> getAllCard();

    Optional <Card> getCardById(Long id);


}
