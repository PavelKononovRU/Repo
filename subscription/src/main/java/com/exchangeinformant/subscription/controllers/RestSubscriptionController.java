package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.dto.TariffDTO;
import com.exchangeinformant.subscription.mappers.SubscriptionMapper;
import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.service.SubscriptionService;
import com.exchangeinformant.subscription.util.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestSubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public RestSubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscriptions/{id}")
    public SubscriptionDTO getSubscription(@PathVariable Long id) {
        Subscription subscription = subscriptionService.getSubscription(id);
        return SubscriptionMapper.INSTANCE.toDTO(subscription);
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<Subscription>> getSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscription());
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
