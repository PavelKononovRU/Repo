package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
}
