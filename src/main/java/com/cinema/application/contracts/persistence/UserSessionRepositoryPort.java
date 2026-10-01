package com.cinema.application.contracts.persistence;

import com.cinema.domain.user.UserSession;
import java.util.Optional;

public interface UserSessionRepositoryPort {
    void save(UserSession session);
    Optional<UserSession> findByTokenHash(String tokenHash);
    void delete(UserSession session);
}