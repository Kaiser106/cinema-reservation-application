package com.cinema.domain.reservation.events;

import com.cinema.domain.common.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationCreatedEvent implements DomainEvent {
    private final UUID reservationId;
    private final UUID userId;
    private final LocalDateTime occurredOn;

    public ReservationCreatedEvent(UUID reservationId, UUID userId) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.occurredOn = LocalDateTime.now();
    }

    public UUID getReservationId() { return reservationId; }
    public UUID getUserId() { return userId; }
    @Override
    public LocalDateTime getOccurredOn() { return occurredOn; }
}