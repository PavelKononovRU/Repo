package com.exchangeinformant.repository;

import com.exchangeinformant.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    public Set<Stock> findAllBySymbol(String stockName);
}
