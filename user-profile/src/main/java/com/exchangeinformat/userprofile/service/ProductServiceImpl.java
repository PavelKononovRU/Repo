package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entityDTO.ProductDTO;
import com.exchangeinformat.userprofile.mappers.ProductMappers;
import com.exchangeinformat.userprofile.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void createProduct(ProductDTO productDTO) {
        productRepository.save(ProductMappers.INSTANCE.productDTOToEntity(productDTO));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return ProductMappers.INSTANCE.productsToDTOs(productRepository.findAll());
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return ProductMappers.INSTANCE.productToDTO(productRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public void updateProduct(ProductDTO productDTO) {
        productRepository.save(ProductMappers.INSTANCE.productDTOToEntity(productDTO));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
