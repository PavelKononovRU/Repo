package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
            return subscriptionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Page<Subscription> getAllSubscriptionsByStatus(String status, int offset, int limit, Pageable pageable) {
        List<Subscription> list = subscriptionRepository.findAll();
        list = list.stream()
                .filter(n -> String.valueOf(n.getStatus()).equals(status))
                .collect(Collectors.toList());
        return new PageImpl<>(list.subList(offset, limit), pageable, list.size());
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
