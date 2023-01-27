package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.service.SubscriptionService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RolesAllowed({"USER", "ADMIN"})
public class AdminRestSubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public AdminRestSubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscriptions")
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<List<SubscriptionDTO>> getSubscription() {
        return ResponseEntity.ok(subscriptionService.getAllSubscription());
    }

    @GetMapping("/get-subscriptions-by-status")
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<Page<SubscriptionDTO>> getSubscriptionsByStatus(@RequestParam String status, @RequestParam int offset, @RequestParam int limit, Pageable pageable) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsWithPagination(status, offset, limit, pageable));
    }

    @GetMapping("/subscriptions/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<SubscriptionDTO> getSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    @PostMapping("/subscriptions")
    @RolesAllowed({"USER", "ADMIN"})
    @ResponseBody
    public ResponseEntity<?> createSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionService.createSubscription(subscriptionDTO);
        return new ResponseEntity<>(subscriptionDTO, HttpStatus.CREATED);
    }

    @PutMapping("/subscriptions")
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionService.updateSubscription(subscriptionDTO);
        return ResponseEntity.ok(subscriptionDTO);
    }

    @DeleteMapping("/subscriptions/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<?> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
