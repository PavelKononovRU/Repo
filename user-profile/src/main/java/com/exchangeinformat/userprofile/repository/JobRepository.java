package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JobRepository extends JpaRepository<Job, Long> {
}
