package com.cinema.application.contracts.persistence;

import com.cinema.domain.payment.Payment;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepositoryPort {
    void save(Payment payment);
    boolean existsByIdempotencyKey(String idempotencyKey);
    Optional<Payment> findById(UUID id);
}