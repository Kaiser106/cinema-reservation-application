package com.cinema.application.contracts.persistence;

import com.cinema.domain.user.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    void save(User user);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
}