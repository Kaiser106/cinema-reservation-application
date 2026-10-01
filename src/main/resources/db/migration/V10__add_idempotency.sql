-- Ensure identical payment requests are strictly prevented at DB level
ALTER TABLE payment ADD CONSTRAINT uq_payment_idempotency UNIQUE(idempotency_key);
ALTER TABLE payment ADD CONSTRAINT uq_payment_transaction_ref UNIQUE(transaction_ref);