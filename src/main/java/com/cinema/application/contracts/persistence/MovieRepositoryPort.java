package com.cinema.application.contracts.persistence;

import com.cinema.domain.movie.Movie;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepositoryPort {
    Optional<Movie> findById(UUID id);
}