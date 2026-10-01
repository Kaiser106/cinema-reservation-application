CREATE TABLE movie (
                       id UUID PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       duration_minutes INT NOT NULL,
                       genre VARCHAR(100),
                       rating VARCHAR(10),
                       poster_url VARCHAR(500),
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL
);

CREATE TABLE session (
                         id UUID PRIMARY KEY,
                         movie_id UUID NOT NULL REFERENCES movie(id),
                         hall_id UUID NOT NULL REFERENCES hall(id),
                         start_time TIMESTAMP NOT NULL,
                         end_time TIMESTAMP NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL,
                         version BIGINT NOT NULL DEFAULT 0 -- For optimistic concurrency
);