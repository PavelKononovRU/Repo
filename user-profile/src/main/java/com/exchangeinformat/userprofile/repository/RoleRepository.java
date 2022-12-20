package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

}
