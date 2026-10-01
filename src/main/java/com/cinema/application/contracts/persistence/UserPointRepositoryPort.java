package com.cinema.application.contracts.persistence;

import com.cinema.domain.point.UserPoint;
import java.util.Optional;
import java.util.UUID;

public interface UserPointRepositoryPort {
    Optional<UserPoint> findByUserId(UUID userId);
    void save(UserPoint userPoint);
}