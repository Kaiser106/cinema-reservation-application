package com.cinema.domain.user;

import com.cinema.domain.common.AggregateRoot;

import java.time.LocalDateTime;
import java.util.UUID;

// WHY: Domain model for database-backed session authentication (No JWT).
public class UserSession extends AggregateRoot {
    private final UUID id;
    private final UUID userId;
    private final String sessionTokenHash;
    private final LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime lastAccessedAt;
    private LocalDateTime revokedAt;

    public UserSession(UUID id, UUID userId, String sessionTokenHash, LocalDateTime expiresAt) {
        this.id = id;
        this.userId = userId;
        this.sessionTokenHash = sessionTokenHash;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = expiresAt;
        this.lastAccessedAt = this.createdAt;
    }

    public void updateLastAccessed() {
        if (isRevoked() || isExpired()) {
            throw new IllegalStateException("Cannot access an expired or revoked session");
        }
        this.lastAccessedAt = LocalDateTime.now();
    }

    public void revoke() {
        this.revokedAt = LocalDateTime.now();
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isRevoked() {
        return revokedAt != null;
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getSessionTokenHash() { return sessionTokenHash; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public LocalDateTime getLastAccessedAt() { return lastAccessedAt; }
    public LocalDateTime getRevokedAt() { return revokedAt; }
}