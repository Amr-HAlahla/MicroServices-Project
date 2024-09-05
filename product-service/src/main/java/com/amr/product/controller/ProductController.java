package com.amr.product.controller;


import com.amr.product.dto.ProductRequest;
import com.amr.product.dto.ProductResponse;
import com.amr.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(
            @RequestBody @Valid ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.saveProduct(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") String productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }


    @PutMapping
    public ResponseEntity<Void> updateProduct(
            @RequestBody @Valid ProductRequest request) {
        productService.updateProduct(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("product-id") String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ProductResponse> searchProducts(
            @RequestParam("name") String name) {
        return ResponseEntity.ok(productService.searchProducts(name));
    }

    @GetMapping("/exists/{product-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("product-id") String productId) {
        return ResponseEntity.ok(productService.existsById(productId));
    }

}
