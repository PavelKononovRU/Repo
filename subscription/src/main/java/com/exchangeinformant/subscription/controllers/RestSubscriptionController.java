package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestSubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public RestSubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscription/{id}")
    public ResponseEntity<Subscription> getSubscription(Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    @GetMapping("/subscription")
    public ResponseEntity<List<Subscription>> getSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscription());
    }

    @PostMapping("/subscription")
    public ResponseEntity<HttpStatus> createSubscription(@RequestBody Subscription subscription) {
        subscriptionService.createSubscription(subscription);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/subscription")
    public ResponseEntity<HttpStatus> updateSubscription(@RequestBody Subscription subscription) {
        subscriptionService.updateSubscription(subscription);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/subscription")
    public ResponseEntity<HttpStatus> deleteSubscription(Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
