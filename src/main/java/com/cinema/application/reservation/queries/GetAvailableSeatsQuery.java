package com.cinema.application.reservation.queries;

import com.cinema.application.common.Query;
import java.util.List;
import java.util.UUID;

public record GetAvailableSeatsQuery(UUID sessionId) implements Query<List<UUID>> {}