package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

}
