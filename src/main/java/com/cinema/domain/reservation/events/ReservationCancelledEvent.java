package com.cinema.domain.reservation.events;

import com.cinema.domain.common.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationCancelledEvent implements DomainEvent {
    private final UUID reservationId;
    private final LocalDateTime occurredOn;

    public ReservationCancelledEvent(UUID reservationId) {
        this.reservationId = reservationId;
        this.occurredOn = LocalDateTime.now();
    }

    public UUID getReservationId() { return reservationId; }
    @Override public LocalDateTime getOccurredOn() { return occurredOn; }
}