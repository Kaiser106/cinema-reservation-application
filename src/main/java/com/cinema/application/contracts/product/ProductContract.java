package com.cinema.application.contracts.product;

import java.util.Optional;
import java.util.UUID;

// WHY: Cross-module communication for checking product stock and details without accessing ProductRepository.
public interface ProductContract {
    Optional<ProductDto> getProductById(UUID productId);
    boolean hasSufficientStock(UUID productId, int requiredQuantity);
}