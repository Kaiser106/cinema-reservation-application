package com.cinema.application.contracts.reservation;

import java.util.Optional;
import java.util.UUID;

// WHY: Allows the Payment module to query reservation status/price without accessing ReservationEntity directly.
public interface ReservationContract {
    Optional<ReservationDto> getReservationById(UUID reservationId);
}