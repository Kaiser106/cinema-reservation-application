package com.cinema.application.product.command;

import com.cinema.application.common.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class PurchaseProductCommand implements Command<Void> {
    private final UUID userId;
    private final UUID productId;
    private final Integer quantity;
}