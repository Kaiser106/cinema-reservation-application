package com.cinema.domain.product.events;

import com.cinema.domain.common.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductPurchasedEvent implements DomainEvent {
    private final UUID productId;
    private final UUID userId;
    private final int quantity;
    private final LocalDateTime occurredOn;

    public ProductPurchasedEvent(UUID productId, UUID userId, int quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.occurredOn = LocalDateTime.now();
    }

    public UUID getProductId() { return productId; }
    public UUID getUserId() { return userId; }
    public int getQuantity() { return quantity; }
    @Override public LocalDateTime getOccurredOn() { return occurredOn; }
}