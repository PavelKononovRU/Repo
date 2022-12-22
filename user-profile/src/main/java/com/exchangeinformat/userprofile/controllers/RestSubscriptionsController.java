package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.model.Subscription;
import com.exchangeinformat.userprofile.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class RestSubscriptionsController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public RestSubscriptionsController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/findOne")
    public ResponseEntity<Subscription> getSubscription(Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createSubscription(@RequestBody Subscription subscription) {
        subscriptionService.createSubscription(subscription);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateSubscription(@RequestBody Subscription subscription) {
        subscriptionService.updateSubscription(subscription);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteSubscription(Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
