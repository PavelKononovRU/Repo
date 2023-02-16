package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.repository.PromoSubscriptionRepository;
import com.exchangeinformant.subscription.service.PromoSubscriptionService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RolesAllowed({"ADMIN", "USER"})
public class RestPromoSubscriptionController {
    private final PromoSubscriptionService promoSubscriptionService;

    @Autowired
    public RestPromoSubscriptionController(PromoSubscriptionService promoSubscriptionService, PromoSubscriptionRepository promoSubscriptionRepository, PromoSubscriptionRepository promoSuлbscriptionRepository) {
        this.promoSubscriptionService = promoSubscriptionService;
    }

    @GetMapping("/promosubscription/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<PromoSubscriptionDTO> getPromoSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(promoSubscriptionService.getPromoSubscription(id));
    }

    @GetMapping("/promosubscription")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<PromoSubscriptionDTO>> getPromoSubscriptions() {
        return ResponseEntity.ok(promoSubscriptionService.getAllPromoSubscription());
    }

    @PostMapping("/promosubscription")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<?> createPromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.createPromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @PutMapping("/promosubscription")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<?> updatePromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.updatePromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @DeleteMapping("/promosubscription/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<?> deletePromoSubscription(@PathVariable Long id) {
        promoSubscriptionService.deletePromoSubscription(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
