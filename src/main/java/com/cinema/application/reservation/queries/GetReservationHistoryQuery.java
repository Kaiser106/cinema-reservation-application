package com.cinema.application.reservation.queries;

import com.cinema.application.common.Query;
import com.cinema.application.contracts.reservation.ReservationDto;
import java.util.List;
import java.util.UUID;

public record GetReservationHistoryQuery(UUID userId) implements Query<List<ReservationDto>> {}