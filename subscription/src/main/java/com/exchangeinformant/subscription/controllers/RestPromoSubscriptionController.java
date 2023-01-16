package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.repository.PromoSubscriptionRepository;
import com.exchangeinformant.subscription.service.PromoSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestPromoSubscriptionController {
    private final PromoSubscriptionService promoSubscriptionService;

    private final PromoSubscriptionRepository promoSubscriptionRepository;


    @Autowired
    public RestPromoSubscriptionController(PromoSubscriptionService promoSubscriptionService, PromoSubscriptionRepository promoSubscriptionRepository) {
        this.promoSubscriptionService = promoSubscriptionService;
        this.promoSubscriptionRepository = promoSubscriptionRepository;
    }

    @GetMapping("/promosubscription/{id}")
    public ResponseEntity<PromoSubscriptionDTO> getPromoSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(promoSubscriptionService.getPromoSubscription(id));
    }

    @GetMapping("/promosubscription")
    public ResponseEntity<List<PromoSubscriptionDTO>> getPromoSubscription() {
        return ResponseEntity.ok(promoSubscriptionService.getAllPromoSubscription());
    }

    @PostMapping("/promosubscription")
    public ResponseEntity<?> createPromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.createPromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @PutMapping("/promosubscription")
    public ResponseEntity<?> updatePromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.updatePromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @DeleteMapping("/promosubscription/{id}")
    public ResponseEntity<?> deletePromoSubscription(@PathVariable Long id) {
        promoSubscriptionService.deletePromoSubscription(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
