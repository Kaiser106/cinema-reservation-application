package com.cinema.domain.point;

import com.cinema.domain.common.AggregateRoot;
import java.util.UUID;

// WHY: Tracks the loyalty points of a user. Points are added after successful payments.
public class UserPoint extends AggregateRoot {
    private final UUID id;
    private final UUID userId;
    private int balance;

    public UserPoint(UUID id, UUID userId) {
        this.id = id;
        this.userId = userId;
        this.balance = 0;
    }

    // Reconstitution constructor
    public UserPoint(UUID id, UUID userId, int balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public void addPoints(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add negative points");
        }
        this.balance += amount;
    }

    public void usePoints(int amount) {
        if (this.balance < amount) {
            throw new IllegalStateException("Insufficient points balance");
        }
        this.balance -= amount;
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public int getBalance() { return balance; }
}