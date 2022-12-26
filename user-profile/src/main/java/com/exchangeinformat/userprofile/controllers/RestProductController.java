package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.Product;
import com.exchangeinformat.userprofile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class RestProductController {
    private final ProductService productService;

    @Autowired
    public RestProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/findOne")
    public ResponseEntity<Product> getProduct(Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteProduct(Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
