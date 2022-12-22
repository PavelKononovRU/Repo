package com.exchangeinformant.subscription.repository;

import com.exchangeinformant.subscription.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

}
