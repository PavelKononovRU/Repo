package com.exchangeinformant.subscription.service;


import com.exchangeinformant.subscription.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    void createSubscription(Subscription subscription);

    Subscription getSubscription(Long id);

    List<Subscription> getAllSubscription();

    void updateSubscription(Subscription subscription);

    void deleteSubscription(Long id);
}
