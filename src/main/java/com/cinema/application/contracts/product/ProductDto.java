package com.cinema.application.contracts.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(UUID id, String name, BigDecimal price, int stock, boolean active) {}