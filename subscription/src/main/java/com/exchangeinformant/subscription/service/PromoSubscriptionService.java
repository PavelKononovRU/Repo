package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.model.PromoSubscription;

import java.util.List;

public interface PromoSubscriptionService {
    List<PromoSubscriptionDTO> getAllPromoSubscription();
    void createPromoSubscription(PromoSubscriptionDTO promoSubscriptionDTO);

    PromoSubscriptionDTO getPromoSubscription(Long id);

    void updatePromoSubscription(PromoSubscriptionDTO promoSubscriptionDTO);

    void deletePromoSubscription(Long id);

}
