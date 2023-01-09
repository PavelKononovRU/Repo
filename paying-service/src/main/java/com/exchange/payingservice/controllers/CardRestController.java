package com.exchange.payingservice.controllers;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.repository.CardRepository;
import com.exchange.payingservice.util.RestControllerAdvice;
import com.exchange.payingservice.service.CardService;
import com.exchange.payingservice.entity.Card;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cards")
public class CardRestController {
    private final CardService cardService;

    public CardRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCard());
    }

    @PostMapping
    public ResponseEntity<Object> saveCard(@RequestBody Card card) {
        cardService.createCard(card);
        return RestControllerAdvice.generateResponsePost(card);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCard(@PathVariable("id") Long id) {
        Card card = cardService.getCardById(id);
        return RestControllerAdvice.generateResponse(CardMapper.INSTANCE.toDTO(card));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCard(@PathVariable("id") Long id, @RequestBody CardDTO cardDTO) {
        cardService.updateCard(id, cardDTO);
        return RestControllerAdvice.generateResponsePut(CardMapper.INSTANCE.toEntity(cardDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCard(@PathVariable("id") Long id) {
        cardService.deleteCard(id);
        return RestControllerAdvice.generateResponseDelete(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return RestControllerAdvice.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
