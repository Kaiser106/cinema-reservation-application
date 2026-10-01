CREATE TABLE product (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DECIMAL(10,2) NOT NULL,
                         stock INT NOT NULL,
                         active BOOLEAN NOT NULL DEFAULT TRUE,
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL
);

CREATE TABLE user_point (
                            id UUID PRIMARY KEY,
                            user_id UUID NOT NULL REFERENCES users(id),
                            balance INT NOT NULL DEFAULT 0,
                            created_at TIMESTAMP NOT NULL,
                            updated_at TIMESTAMP NOT NULL
);