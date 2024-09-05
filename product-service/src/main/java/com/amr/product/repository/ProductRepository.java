package com.amr.product.repository;

import com.amr.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String name);
}
