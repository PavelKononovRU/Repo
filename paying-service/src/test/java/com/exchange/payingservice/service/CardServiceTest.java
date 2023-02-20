package com.exchange.payingservice.service;

import com.exchange.payingservice.IntegrationTestBase;
import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.exceptions.PaymentException;
import com.exchange.payingservice.repository.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class CardServiceTest extends IntegrationTestBase {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @Test
    @DisplayName("Should create new card")
    void shouldCreateNewCard() {
        CardDTO cardDTO = new CardDTO("1234-1231-1235-7665", "user55", "754", 23L);
        cardService.createCard(cardDTO);
        assertEquals(cardDTO.getNumber(), cardRepository.getCardByNumber("1234-1231-1235-7665").getNumber());
    }

    @Test
    @DisplayName("Should get card by id")
    void shouldGetCardById() {
        Optional<Card> maybeCard = Optional.ofNullable(cardService.getCardById(1L));
        assertTrue(maybeCard.isPresent());
        maybeCard.ifPresent(entity -> assertEquals("2101-2222-3333-4444", entity.getNumber()));
    }

    @Test
    @DisplayName("Should get all cards")
    void shouldGetAllCards() {
        List<CardDTO> list = cardService.getAllCard();
        assertEquals(2, list.size());
        assertEquals(list.get(0).getNumber(), cardService.getCardById(1L).getNumber());
        assertEquals(list.get(1).getNumber(), cardService.getCardById(2L).getNumber());

    }

    @Test
    @DisplayName("Should update card by id")
    void shouldUpdateCardById() {
        assertEquals("2101-2222-3333-4444", cardService.getCardById(1L).getNumber());
        CardDTO cardDTO = new CardDTO(1L,
                "1234-1234-1234-5112",
                "user123",
                "333",
                23L);
        cardService.updateCard(1L, cardDTO);
        assertEquals(cardDTO.getNumber(), cardService.getCardById(1L).getNumber());
        assertNotNull(cardDTO.getId());
    }

    @Test
    @ExceptionHandler(PaymentException.class)
    void shouldDeleteCardById() {
        cardService.deleteCard(1L);
        PaymentException exception = Assertions.assertThrows(PaymentException.class, () ->
                assertNull(cardService.getCardById(1L)));
        Assertions.assertEquals("Карта с номером 1 не найдена", exception.getMessage());
    }

}
