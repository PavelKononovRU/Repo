package com.exchangeinformat.userprofile.repository;

import com.exchangeinformat.userprofile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
