package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.repository.PromoSubscriptionRepository;
import com.exchangeinformant.subscription.service.PromoSubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Контроллер промоподписок", description = "CRUD операции с промоподписками.")
public class RestPromoSubscriptionController {
    private final PromoSubscriptionService promoSubscriptionService;
    private final PromoSubscriptionRepository promoSubscriptionRepository;


    @Autowired
    public RestPromoSubscriptionController(PromoSubscriptionService promoSubscriptionService, PromoSubscriptionRepository promoSubscriptionRepository) {
        this.promoSubscriptionService = promoSubscriptionService;
        this.promoSubscriptionRepository = promoSubscriptionRepository;
    }

    @GetMapping("/promosubscription/{id}")
    @Operation(summary = "Получить промоподписку по идентификатору.")
    public ResponseEntity<PromoSubscriptionDTO> getPromoSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(promoSubscriptionService.getPromoSubscription(id));
    }

    @GetMapping("/promosubscription")
    @Operation(summary = "Получить все промоподписки.")
    public ResponseEntity<List<PromoSubscriptionDTO>> getPromoSubscription() {
        return ResponseEntity.ok(promoSubscriptionService.getAllPromoSubscription());
    }

    @PostMapping("/promosubscription")
    @Operation(summary = "Создать промоподписку.")
    public ResponseEntity<?> createPromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.createPromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @PutMapping("/promosubscription")
    @Operation(summary = "Изменить промоподписку.")
    public ResponseEntity<?> updatePromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.updatePromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @DeleteMapping("/promosubscription/{id}")
    @Operation(summary = "Удалить промоподписку.")
    public ResponseEntity<?> deletePromoSubscription(@PathVariable Long id) {
        promoSubscriptionService.deletePromoSubscription(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
