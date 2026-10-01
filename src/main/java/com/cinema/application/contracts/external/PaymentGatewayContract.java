package com.cinema.application.contracts.external;

import java.math.BigDecimal;
import java.util.UUID;

// WHY: Application layer must not know about Iyzipay.
// Infrastructure will implement this interface using Iyzipay libraries.
public interface PaymentGatewayContract {
    PaymentResult processPayment(UUID reservationId, BigDecimal amount, String paymentMethod, String idempotencyKey);
}