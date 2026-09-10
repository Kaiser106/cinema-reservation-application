package com.cinema.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReservationSeat {
    private UUID id;
    private UUID seatId;
    private UUID reservationId;
    private UUID sessionId;
    private BigDecimal price;
}