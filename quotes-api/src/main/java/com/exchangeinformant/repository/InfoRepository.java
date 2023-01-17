package com.exchangeinformant.repository;

import com.exchangeinformant.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 07.01.2023
 * Time: 20:21
 */

public interface InfoRepository extends JpaRepository<Info, Long> {

    @Query("select u from Info u where u.secureCode = :secure and u.updatedAt >= :from and u.updatedAt <= :to")
    List<Info> getInfoBySecureCodeAndDates(@Param("secure") String secure,
                                           @Param("from") LocalDateTime from,
                                           @Param("to")LocalDateTime to);

    Info findFirstBySecureCodeOrderByUpdatedAt(String secureCode);
}
