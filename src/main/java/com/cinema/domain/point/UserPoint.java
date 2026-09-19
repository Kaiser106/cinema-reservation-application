package com.cinema.domain.point;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserPoint {
    private UUID id;
    private UUID userId;
    private Integer balance;
    private LocalDateTime updatedAt;

    public UserPoint(UUID id, UUID userId, Integer balance, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.updatedAt = updatedAt;
    }

    public void addPoints(int points) {
        this.balance += points;
        this.updatedAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public Integer getBalance() { return balance; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}