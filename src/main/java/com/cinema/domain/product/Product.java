package com.cinema.domain.product;

import com.cinema.domain.common.AggregateRoot;
import com.cinema.domain.common.Money;
import java.util.UUID;

// WHY: Products (like popcorn, drinks) can be bought separately or together with reservations.
public class Product extends AggregateRoot {
    private final UUID id;
    private String name;
    private String description;
    private Money price;
    private int stock;
    private boolean active;

    public Product(UUID id, String name, String description, Money price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.active = true;
    }

    public void decreaseStock(int quantity) {
        if (this.stock < quantity) {
            throw new IllegalStateException("Not enough stock available");
        }
        this.stock -= quantity;
    }

    public void deactivate() {
        this.active = false;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Money getPrice() { return price; }
    public int getStock() { return stock; }
    public boolean isActive() { return active; }
}