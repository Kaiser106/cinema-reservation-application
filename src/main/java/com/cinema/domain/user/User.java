package com.cinema.domain.user;

import com.cinema.domain.common.AggregateRoot;
import java.util.UUID;

// WHY: User is separated from Customer. User handles authentication and authorization (credentials, roles).
// Customer handles business details (name, phone). This separation of concerns prevents the auth logic
// from being bloated with business profile data.
public class User extends AggregateRoot {
    private final UUID id;
    private final String email;
    private String passwordHash;
    private final UserRole role;

    public User(UUID id, String email, String passwordHash, UserRole role) {
        if (id == null || email == null || passwordHash == null || role == null) {
            throw new IllegalArgumentException("User properties cannot be null");
        }
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public void updatePassword(String newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public UserRole getRole() { return role; }
}