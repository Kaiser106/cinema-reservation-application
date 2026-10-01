CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL,
                       deleted_at TIMESTAMP
);

CREATE TABLE customer (
                          id UUID PRIMARY KEY,
                          user_id UUID NOT NULL UNIQUE REFERENCES users(id),
                          first_name VARCHAR(100) NOT NULL,
                          last_name VARCHAR(100) NOT NULL,
                          phone VARCHAR(20),
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP NOT NULL,
                          deleted_at TIMESTAMP
);