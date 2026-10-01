-- Represents database-backed session authentication, not movie sessions.
CREATE TABLE user_session (
                              id UUID PRIMARY KEY,
                              user_id UUID NOT NULL REFERENCES users(id),
                              session_token_hash VARCHAR(255) NOT NULL,
                              expires_at TIMESTAMP NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              last_accessed_at TIMESTAMP NOT NULL,
                              revoked_at TIMESTAMP
);