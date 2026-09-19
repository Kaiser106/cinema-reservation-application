package com.cinema.domain.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID reservationId;
    private BigDecimal amount;
    private String paymentMethod;
    private PaymentStatus status;
    private LocalDateTime paidAt;
    private String transactionRef;

    public Payment(UUID id, UUID reservationId, BigDecimal amount, String paymentMethod, PaymentStatus status, LocalDateTime paidAt, String transactionRef) {
        this.id = id;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paidAt = paidAt;
        this.transactionRef = transactionRef;
    }

    public UUID getId() { return id; }
    public UUID getReservationId() { return reservationId; }
    public BigDecimal getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public PaymentStatus getStatus() { return status; }
    public LocalDateTime getPaidAt() { return paidAt; }
    public String getTransactionRef() { return transactionRef; }
}