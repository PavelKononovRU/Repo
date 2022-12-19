package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
