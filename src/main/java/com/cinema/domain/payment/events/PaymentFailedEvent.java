package com.cinema.domain.payment.events;

import com.cinema.domain.common.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentFailedEvent implements DomainEvent {
    private final UUID paymentId;
    private final UUID reservationId;
    private final String reason;
    private final LocalDateTime occurredOn;

    public PaymentFailedEvent(UUID paymentId, UUID reservationId, String reason) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.reason = reason;
        this.occurredOn = LocalDateTime.now();
    }

    public UUID getPaymentId() { return paymentId; }
    public UUID getReservationId() { return reservationId; }
    public String getReason() { return reason; }
    @Override public LocalDateTime getOccurredOn() { return occurredOn; }
}