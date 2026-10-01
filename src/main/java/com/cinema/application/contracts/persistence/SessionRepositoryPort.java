package com.cinema.application.contracts.persistence;

import com.cinema.domain.movie.Session;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepositoryPort {
    Optional<Session> findById(UUID id);
}