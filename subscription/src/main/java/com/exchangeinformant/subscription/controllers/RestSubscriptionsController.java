package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.mappers.SubscriptionMapper;
import com.exchangeinformant.subscription.mappers.Wrapper;
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
    public SubscriptionDTO getSubscription(@PathVariable Long id) {
        Subscription subscription = subscriptionService.getSubscription(id);
        return SubscriptionMapper.INSTANCE.toDTO(subscription);
    }

    @GetMapping("/subscriptions")
    public Page<SubscriptionDTO> getSubscriptions(@RequestParam String status, @RequestParam int offset, @RequestParam int limit, Pageable pageable){
        Page<Subscription> page = subscriptionService.getAllSubscriptionsByStatus(status, offset, limit, pageable);
        Wrapper<Page<SubscriptionDTO>> wrapper = SubscriptionMapper.INSTANCE.toPageDTO(page);
        return wrapper.getValue();
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
