package com.exchange.payingservice.controllers;

import com.exchange.payingservice.util.ResponseHandler;
import com.exchange.payingservice.util.StatusCards;
import com.exchange.payingservice.service.CardService;
import com.exchange.payingservice.entity.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cards")
public class CardRestController {
    private final CardService cardService;


    public CardRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCardController(@RequestBody Card card) {
        StatusCards result = cardService.createCard(card);
        return ResponseHandler.generateResponse(result);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
    }
}
