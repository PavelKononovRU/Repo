package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.model.PromoSubscription;
import com.exchangeinformant.subscription.repository.PromoSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PromoSubscriptionServiceImpl implements PromoSubscriptionService {

    private final PromoSubscriptionRepository promoSubscriptionRepository;

    @Autowired
    public PromoSubscriptionServiceImpl(PromoSubscriptionRepository promoSubscriptionRepository) {
        this.promoSubscriptionRepository = promoSubscriptionRepository;
    }

    @Override
    public List<PromoSubscription> getAllPromoSubscription() {
        return promoSubscriptionRepository.findAll();
    }

    @Override
    @Transactional
    public void createPromoSubscription(PromoSubscription promoSubscription) { promoSubscriptionRepository.save(promoSubscription);
    }
    @Override
    public PromoSubscription getPromoSubscription(Long id) {
        return promoSubscriptionRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public void updatePromoSubscription(PromoSubscription promoSubscription) { promoSubscriptionRepository.save(promoSubscription);
    }
    @Override
    @Transactional
    public void deletePromoSubscription(Long id) {promoSubscriptionRepository.deleteById(id);
    }
}
