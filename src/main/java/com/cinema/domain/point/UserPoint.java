package com.cinema.domain.point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserPoint {
    private UUID id;
    private UUID userId;
    private Integer balance;
    private LocalDateTime updatedAt;

    public void addPoints(int points) {
        this.balance += points;
        this.updatedAt = LocalDateTime.now();
    }
}