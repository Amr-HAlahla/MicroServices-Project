package com.amr.product.mapper;

import com.amr.product.dto.ProductRequest;
import com.amr.product.dto.ProductResponse;
import com.amr.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    // From ProductRequest to Product
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
    }

    // From Product to ProductResponse
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

}
