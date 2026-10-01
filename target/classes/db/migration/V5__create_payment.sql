CREATE TABLE payment (
                         id UUID PRIMARY KEY,
                         reservation_id UUID NOT NULL REFERENCES reservation(id),
                         amount DECIMAL(10,2) NOT NULL,
                         payment_method VARCHAR(50) NOT NULL,
                         status VARCHAR(50) NOT NULL,
                         paid_at TIMESTAMP,
                         transaction_ref VARCHAR(255),
                         idempotency_key VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL
);