package com.cinema.application.contracts.persistence;

import com.cinema.domain.product.Product;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Optional<Product> findById(UUID id);
    void save(Product product);
}