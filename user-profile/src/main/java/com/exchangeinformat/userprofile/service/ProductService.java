package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entity.Product;

import java.util.List;

public interface ProductService {
    void createProduct(Product product);

    List<Product> getAllProducts();
    Product getProduct(Long id);

    void updateProduct(Product product);

    void deleteProduct(Long id);
}
