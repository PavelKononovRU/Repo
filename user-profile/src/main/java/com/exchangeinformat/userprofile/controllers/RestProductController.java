package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entityDTO.ProductDTO;
import com.exchangeinformat.userprofile.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Контроллер продуктов пользователей", description = "CRUD операции с продуктами пользователя")
@RestController
@RequestMapping("/api/product")
public class RestProductController {
    private final ProductService productService;

    @Autowired
    public RestProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Получение продукта пользователя", description = "Метод принимает в параметры long ID продукта, доступ только у админа")
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @Operation(summary = "Получение всех продуктов пользователей", description = "Доступ только у админа")
    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Создание продукта", description = "Метод принимает в параметры ProductDT, доступ только у админа")
    @PostMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductDTO productDTO) {
        productService.createProduct(productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Обновление продукта", description = "Метод принимает в параметры ProductDTO, доступ только у админа")
    @PutMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление продукта", description = "Метод принимает в параметры long ID, доступ только у админа")
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
