package com.cinema.domain.reservation;

import com.cinema.domain.common.Money;

import java.util.UUID;

// WHY: A nested entity inside the Reservation aggregate.
// Its lifecycle is bound to the Reservation. It shouldn't be loaded directly by external systems.
public class ReservationSeat {
    private final UUID id;
    private final UUID reservationId;
    private final UUID sessionId;
    private final UUID seatId;
    private final Money price;

    public ReservationSeat(UUID id, UUID reservationId, UUID sessionId, UUID seatId, Money price) {
        this.id = id;
        this.reservationId = reservationId;
        this.sessionId = sessionId;
        this.seatId = seatId;
        this.price = price;
    }

    public UUID getId() { return id; }
    public UUID getReservationId() { return reservationId; }
    public UUID getSessionId() { return sessionId; }
    public UUID getSeatId() { return seatId; }
    public Money getPrice() { return price; }
}