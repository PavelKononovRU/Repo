package com.exchange.payingservice.controllers;

import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.util.RestControllerAdvice;
import com.exchange.payingservice.service.CardService;
import com.exchange.payingservice.entity.Card;
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
       ;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return RestControllerAdvice.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
