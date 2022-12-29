package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestSubscriptionsController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public RestSubscriptionsController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscriptions/{id}")
    public Subscription getSubscription(@PathVariable Long id) {
        return subscriptionService.getSubscription(id);
    }

    @GetMapping("/subscriptions")
    public Page<Subscription> getSubscriptions(@RequestParam String status, @RequestParam int offset, @RequestParam int limit, Pageable pageable){
        return subscriptionService.getAllSubscriptionsByStatus(status, offset, limit, pageable);
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<HttpStatus> createSubscription(@RequestBody Subscription subscription) {
        subscriptionService.createSubscription(subscription);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/subscriptions")
    public ResponseEntity<HttpStatus> updateSubscription(@RequestBody Subscription subscription) {
        subscriptionService.updateSubscription(subscription);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<HttpStatus> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
