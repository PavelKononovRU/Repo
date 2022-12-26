package com.exchangeinformant.subscription.repository;


import com.exchangeinformant.subscription.model.PromoSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoSubscriptionRepository extends JpaRepository<PromoSubscription, Long> {
}
