package com.exchange.payingservice.controller;

import com.exchange.payingservice.Service.CardService;
import com.exchange.payingservice.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/findOne")
    public ResponseEntity<Card> getCard(Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCard());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCard(@RequestBody Card card) {
        cardService.createCard(card);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateCard(@RequestBody Card card) {
        cardService.updateCard(card);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteCard(Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
