package com.cinema.application.contracts.persistence;

import com.cinema.domain.payment.Payment;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);
}