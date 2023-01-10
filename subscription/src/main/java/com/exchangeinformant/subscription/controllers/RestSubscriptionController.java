package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RestSubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public RestSubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscription());
    }

    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<SubscriptionDTO> getSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    @PostMapping("/subscriptions")
    @ResponseBody
    public ResponseEntity<?> createSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionService.createSubscription(subscriptionDTO);
        return  ResponseEntity.ok(subscriptionDTO);
    }

    @PutMapping("/subscriptions")
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionService.updateSubscription(subscriptionDTO);
        return ResponseEntity.ok(subscriptionDTO);
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok(id);
    }
}
