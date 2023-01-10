package com.exchangeinformant.repository;

import com.exchangeinformant.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<Info,Long> {
    Info findCompanyBySecureCode(String secureCode);
}
