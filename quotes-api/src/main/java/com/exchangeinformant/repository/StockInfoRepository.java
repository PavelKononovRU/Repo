package com.exchangeinformant.repository;

import com.exchangeinformant.model.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInfoRepository extends JpaRepository<StockInfo,Long> {
}
