package com.cinema.domain.payment.events;

import com.cinema.domain.common.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentSucceededEvent implements DomainEvent {
    private final UUID paymentId;
    private final UUID reservationId;
    private final LocalDateTime occurredOn;

    public PaymentSucceededEvent(UUID paymentId, UUID reservationId) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.occurredOn = LocalDateTime.now();
    }

    public UUID getPaymentId() { return paymentId; }
    public UUID getReservationId() { return reservationId; }
    @Override public LocalDateTime getOccurredOn() { return occurredOn; }
}