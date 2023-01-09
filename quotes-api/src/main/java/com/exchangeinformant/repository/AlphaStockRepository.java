package com.exchangeinformant.repository;

import com.exchangeinformant.model.AlphaStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AlphaStockRepository extends JpaRepository<AlphaStock,Long> {
}
