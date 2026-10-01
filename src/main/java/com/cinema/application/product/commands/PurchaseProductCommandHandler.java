package com.cinema.application.product.commands;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.persistence.OutboxPort;
import com.cinema.application.contracts.persistence.ProductRepositoryPort;
import com.cinema.domain.product.Product;
import com.cinema.domain.product.events.ProductPurchasedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PurchaseProductCommandHandler implements CommandHandler<PurchaseProductCommand, UUID> {

    private final ProductRepositoryPort productRepositoryPort;
    private final OutboxPort outboxPort;

    public PurchaseProductCommandHandler(ProductRepositoryPort productRepositoryPort, OutboxPort outboxPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.outboxPort = outboxPort;
    }

    @Override
    @Transactional
    public UUID handle(PurchaseProductCommand command) {
        Product product = productRepositoryPort.findById(command.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.decreaseStock(command.quantity());

        outboxPort.saveEvents(
                "Product",
                product.getId().toString(),
                List.of(new ProductPurchasedEvent(product.getId(), command.userId(), command.quantity()))
        );

        productRepositoryPort.save(product);
        return product.getId();
    }
}