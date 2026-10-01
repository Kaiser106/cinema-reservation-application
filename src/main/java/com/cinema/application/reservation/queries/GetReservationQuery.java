package com.cinema.application.reservation.queries;

import com.cinema.application.common.Query;
import com.cinema.application.contracts.reservation.ReservationDto;
import java.util.UUID;

public record GetReservationQuery(UUID reservationId) implements Query<ReservationDto> {}