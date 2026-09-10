package com.cinema.domain.reservation.events;

import com.cinema.domain.common.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReservationCreatedEvent implements DomainEvent {
    private final UUID reservationId;
    private final UUID userId;
    private final LocalDateTime occurredOn = LocalDateTime.now();
}