package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.model.PromoSubscription;
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

    @Autowired
    public RestPromoSubscriptionController(PromoSubscriptionService promoSubscriptionService) {
        this.promoSubscriptionService = promoSubscriptionService;
    }

    @GetMapping("/promosubscriptions/{id}")
    public ResponseEntity<PromoSubscription> getPromoSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(promoSubscriptionService.getPromoSubscription(id));
    }

    @GetMapping("/promosubscriptions")
    public ResponseEntity<List<PromoSubscription>> getPromoSubscriptions() {
        return ResponseEntity.ok(promoSubscriptionService.getAllPromoSubscription());
    }

    @PostMapping("/promosubscriptions")
    public ResponseEntity<HttpStatus> createPromoSubscription(@RequestBody PromoSubscription promosubscription) {
        promoSubscriptionService.createPromoSubscription(promosubscription);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/promosubscriptions")
    public ResponseEntity<HttpStatus> updatePromoSubscription(@RequestBody PromoSubscription promosubscription) {
        promoSubscriptionService.updatePromoSubscription(promosubscription);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/promosubscriptions")
    public ResponseEntity<HttpStatus> deletePromoSubscription(Long id) {
        promoSubscriptionService.deletePromoSubscription(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
