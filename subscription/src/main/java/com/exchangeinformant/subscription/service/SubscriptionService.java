package com.exchangeinformant.subscription.service;


import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.exception.UserNotFoundException;
import com.exchangeinformant.subscription.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    void createSubscription(SubscriptionDTO subscriptionDTO);

    SubscriptionDTO getSubscription(Long id) ;

    List<SubscriptionDTO> getAllSubscription();

    void updateSubscription(SubscriptionDTO subscriptionDTO);

    void deleteSubscription(Long id);
}
