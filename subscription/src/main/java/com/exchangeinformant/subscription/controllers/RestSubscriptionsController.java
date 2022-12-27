package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestSubscriptionsController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public RestSubscriptionsController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<Page<Subscription>> getSubscriptionsByStatus(@RequestParam String status,
        @RequestParam int offset, @RequestParam int limit, Pageable pageable) {
        List<Subscription> list = subscriptionService.getAllSubscriptions();
        list = list.stream()
                .filter(n -> String.valueOf(n.getStatus()).equals(status))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(list.subList(offset, limit), pageable, list.size()));
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
