package com.amr.product.service;


import com.amr.product.dto.ProductRequest;
import com.amr.product.dto.ProductResponse;
import com.amr.product.exception.ProductNotFoundException;
import com.amr.product.mapper.ProductMapper;
import com.amr.product.model.Product;
import com.amr.product.repository.ProductRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public String saveProduct(ProductRequest request) {
        var product = productRepository.save(productMapper.toProduct(request));
        return product.getId();
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    public ProductResponse findById(String productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                                String.format("No product found with ID:: %s", productId)
                        )
                );
    }

    public void updateProduct(ProductRequest request) {
        var product = productRepository.findById(request.id())
                .orElseThrow(() -> new ProductNotFoundException(
                                String.format("Cannot update product:: No product found with ID:: %s", request.id())
                        )
                );
        mergeProduct(product, request);
        productRepository.save(product);
    }

    private void mergeProduct(Product product, ProductRequest request) {
        if (StringUtils.isNotBlank(request.name())) {
            product.setName(request.name());
        }
        if (StringUtils.isNotBlank(request.description())) {
            product.setDescription(request.description());
        }
        if (request.price() != null) {
            product.setPrice(request.price());
        }
    }

    public Boolean existsById(String productId) {
        return productRepository.findById(productId).isPresent();
    }

    public void deleteProduct(String productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(
                    String.format("No product found with ID:: %s", productId)
            );
        }
        productRepository.deleteById(productId);
    }

    public ProductResponse searchProducts(String name) {
        var product = productRepository.findByName(name);
        if (product == null) {
            throw new ProductNotFoundException(
                    String.format("No product found with name:: %s", name)
            );
        }
        return productMapper.toProductResponse(product);
    }


}
