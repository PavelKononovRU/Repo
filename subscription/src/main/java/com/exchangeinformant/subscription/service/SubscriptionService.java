package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionService {

    void createSubscription(Subscription subscription);

    Subscription getSubscription(Long id);

    Page<Subscription> getAllSubscriptionsByStatus(String status, int offset, int limit, Pageable pageable);

    void updateSubscription(Subscription subscription);

    void deleteSubscription(Long id);

}
