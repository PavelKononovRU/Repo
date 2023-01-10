package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public interface CardService {

    CardDTO createCard(@Valid CardDTO card);

    void updateCard(Long id,@Valid CardDTO cardDTO);

    void deleteCard(Long id);

    List<Card> getAllCard();

    Card getCardById(Long id);


}
