package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByExtId(String exId);
    boolean existsByExtId(String exId);

}
