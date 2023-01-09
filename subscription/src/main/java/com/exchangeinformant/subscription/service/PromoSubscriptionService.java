package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.model.PromoSubscription;

import java.util.List;

public interface PromoSubscriptionService {
    List<PromoSubscription> getAllPromoSubscription();
    void createPromoSubscription(PromoSubscription promoSubscription);

    PromoSubscription getPromoSubscription(Long id);

    void updatePromoSubscription(PromoSubscription promoSubscription);

    void deletePromoSubscription(Long id);

}
