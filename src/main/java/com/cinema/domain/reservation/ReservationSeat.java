package com.cinema.domain.reservation;

import java.math.BigDecimal;
import java.util.UUID;

public class ReservationSeat {
    private UUID id;
    private UUID seatId;
    private UUID reservationId;
    private UUID sessionId;
    private BigDecimal price;

    public ReservationSeat(UUID id, UUID seatId, UUID reservationId, UUID sessionId, BigDecimal price) {
        this.id = id;
        this.seatId = seatId;
        this.reservationId = reservationId;
        this.sessionId = sessionId;
        this.price = price;
    }

    public UUID getId() { return id; }
    public UUID getSeatId() { return seatId; }
    public UUID getReservationId() { return reservationId; }
    public UUID getSessionId() { return sessionId; }
    public BigDecimal getPrice() { return price; }
}