package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
}
