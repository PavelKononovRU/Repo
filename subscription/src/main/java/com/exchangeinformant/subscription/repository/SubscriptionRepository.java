package com.exchangeinformant.subscription.repository;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
