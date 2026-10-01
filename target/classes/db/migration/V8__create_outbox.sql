CREATE TABLE outbox_event (
                              id UUID PRIMARY KEY,
                              event_type VARCHAR(100) NOT NULL,
                              aggregate_type VARCHAR(100) NOT NULL,
                              aggregate_id VARCHAR(100) NOT NULL,
                              payload TEXT NOT NULL,
                              status VARCHAR(50) NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              processed_at TIMESTAMP,
                              retry_count INT NOT NULL DEFAULT 0
);