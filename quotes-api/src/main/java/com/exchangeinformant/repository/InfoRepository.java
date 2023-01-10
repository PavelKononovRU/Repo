package com.exchangeinformant.repository;

import com.exchangeinformant.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 07.01.2023
 * Time: 20:21
 */

public interface InfoRepository extends JpaRepository<Info, Long> {
    Info findBySecureCode(String code);
}
