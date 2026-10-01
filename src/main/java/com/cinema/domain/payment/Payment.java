package com.cinema.domain.payment;

import com.cinema.domain.common.AggregateRoot;
import com.cinema.domain.common.Money;
import java.time.LocalDateTime;
import java.util.UUID;

// WHY: Payment must track its idempotency key. Since clients can retry failed network requests,
// the idempotencyKey guarantees we do not charge the user twice for the same reservation attempt.
public class Payment extends AggregateRoot {
    private final UUID id;
    private final UUID reservationId;
    private final Money amount;
    private final String paymentMethod;
    private PaymentStatus status;
    private final String idempotencyKey;
    private String transactionRef;
    private LocalDateTime paidAt;

    public Payment(UUID id, UUID reservationId, Money amount, String paymentMethod, String idempotencyKey) {
        this.id = id;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.idempotencyKey = idempotencyKey;
        this.status = PaymentStatus.PENDING;
    }

    // Reconstitution constructor
    public Payment(UUID id, UUID reservationId, Money amount, String paymentMethod, PaymentStatus status,
                   String idempotencyKey, String transactionRef, LocalDateTime paidAt) {
        this.id = id;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.idempotencyKey = idempotencyKey;
        this.transactionRef = transactionRef;
        this.paidAt = paidAt;
    }

    public void markAsSuccess(String transactionRef, LocalDateTime paidAt) {
        if (this.status == PaymentStatus.SUCCESS) {
            throw new IllegalStateException("Payment is already successful");
        }
        this.status = PaymentStatus.SUCCESS;
        this.transactionRef = transactionRef;
        this.paidAt = paidAt;
    }

    public void markAsFailed() {
        this.status = PaymentStatus.FAILED;
    }

    public UUID getId() { return id; }
    public UUID getReservationId() { return reservationId; }
    public Money getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public PaymentStatus getStatus() { return status; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public String getTransactionRef() { return transactionRef; }
    public LocalDateTime getPaidAt() { return paidAt; }
}