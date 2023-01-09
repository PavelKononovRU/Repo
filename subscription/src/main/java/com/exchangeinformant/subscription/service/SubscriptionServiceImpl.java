package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.exception.ResourceNotFoundException;
import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    @Transactional
    public void createSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getSubscription(Long id) {
            return subscriptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Подписка с id " + id + " не найдена"));
    }


    @Override
    public List<Subscription> getAllSubscription() {
        return subscriptionRepository.findAll();
    }

    @Override
    @Transactional
    public void updateSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
