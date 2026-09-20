package com.cinema.application.contracts.persistence;

import com.cinema.domain.product.Product;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<Product> findById(UUID id);
    Product save(Product product);
}