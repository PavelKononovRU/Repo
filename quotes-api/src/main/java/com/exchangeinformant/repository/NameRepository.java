package com.exchangeinformant.repository;

import com.exchangeinformant.dto.NameDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends JpaRepository<NameDTO, String> {
}
