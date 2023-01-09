package com.exchangeinformant.repository;

import com.exchangeinformant.model.TinkoffStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinkoffRepository extends JpaRepository<TinkoffStock,Long> {
}
