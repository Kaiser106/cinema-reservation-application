package com.cinema.application.contracts.point;

import java.util.UUID;

// WHY: Ensures modules like Payment can add points to a user after a successful transaction without knowing Point internals.
public interface PointContract {
    void addPointsToUser(UUID userId, int amount);
    void deductPointsFromUser(UUID userId, int amount);
    int getUserPointBalance(UUID userId);
}