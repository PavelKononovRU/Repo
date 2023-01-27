package com.exchange.payingservice.service;

import com.exchange.payingservice.IntegrationTestBase;
import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.repository.CardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class CardServiceTest extends IntegrationTestBase {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetById() {
        Optional<Card> maybeCard = cardService.getCardById(1L);
        assertTrue(maybeCard.isPresent());
        maybeCard.ifPresent(entity -> {assertEquals("1111-2222-3333-4444", entity.getNumber());
        });
    }

    @Test
    void testUpdate() {
        CardDTO cardDTO = CardMapper.INSTANCE.toDTO(cardService.getCardById(1L).get());
        cardDTO.setCSV("333");
        cardDTO.setNumber("1234-1234-1234-5112");
        cardDTO.setPrincipal("user123");
        cardDTO.setUser_id(23L);
        cardService.createCard(cardDTO);
        assertEquals(cardDTO.getNumber(), cardService.getCardById(1L).get().getNumber());
        assertNotNull(cardDTO.getId());
    }

    @Test
    void testDelete() {
        cardService.deleteCard(1L);
        Optional<Card> maybeCard = cardService.getCardById(1L);
        maybeCard.ifPresent(entity -> {
            assertNull(entity.getNumber());
            assertEquals(entity.getNumber(), "1111-2222-3333-4444");
        });
    }

    @Test
    void testSave() {
        CardDTO cardDTO = new CardDTO("1234-1231-1235-7665", "user55", "754", 23L);
        cardService.createCard(cardDTO);
        assertEquals(cardDTO.getNumber(), cardRepository.getCardByNumber("1234-1231-1235-7665").getNumber());
    }

}
