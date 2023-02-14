package com.exchange.payingservice.controllers;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.mappers.PaymentsMapper;
import com.exchange.payingservice.util.StatusCards;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Контроллер карт", description = "CRUD операции с картами.")
@RestController
@RequestMapping("/api/cards")
public class CardRestController {
    private final CardService cardService;

    public CardRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @Operation(summary = "Получение всех карт.")
    @GetMapping
    public ResponseEntity<List<CardDTO>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCard());
    }

    @Operation(summary = "Добавление новой карты.")
    @PostMapping
    public ResponseEntity<StatusCards> saveCard(@Valid @RequestBody CardDTO cardDTO) {
        cardService.createCard(cardDTO);
        return RestControllerAdvice.generateResponsePost(cardDTO);
    }

    @Operation(summary = "Получение карты по идентификатору.")
    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable @Parameter(description = "Идентификатор карты.") Long id) {
        CardDTO cardDTO = CardMapper.INSTANCE.toDTO(cardService.getCardById(id));
        return RestControllerAdvice.generateResponse(cardDTO);
    }

    @Operation(summary = "Обновление карты по идентификатору.")
    @PutMapping("/{id}")
    public ResponseEntity<StatusCards> updateCard(@PathVariable @Parameter(description = "Идентификатор карты.") Long id,
                                             @Valid @RequestBody CardDTO cardDTO) {
        cardService.updateCard(id, cardDTO);
        return RestControllerAdvice.generateResponsePut();
    }

    @Operation(summary = "Удаление карты по идентификатору.")
    @DeleteMapping("/{id}")
    public ResponseEntity<StatusCards> deleteCard(@PathVariable @Parameter(description = "Идентификатор карты.") Long id) {
        cardService.deleteCard(id);
        return RestControllerAdvice.generateResponseDelete(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return RestControllerAdvice.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
