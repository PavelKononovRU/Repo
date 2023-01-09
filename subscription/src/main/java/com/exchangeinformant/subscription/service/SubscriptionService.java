package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SubscriptionService {

    void createSubscription(SubscriptionDTO subscriptionDTO);

    SubscriptionDTO getSubscription(Long id) ;

    List<SubscriptionDTO> getAllSubscription();

    Page<SubscriptionDTO> getSubscriptionsWithPagination(String status, int offset, int limit, Pageable pageable);

    void updateSubscription(SubscriptionDTO subscriptionDTO);

    void deleteSubscription(Long id);

}
