package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entityDTO.ProductDTO;

import java.util.List;

public interface ProductService {
    void createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    ProductDTO getProduct(Long id);

    void updateProduct(ProductDTO productDTO);

    void deleteProduct(Long id);
}
