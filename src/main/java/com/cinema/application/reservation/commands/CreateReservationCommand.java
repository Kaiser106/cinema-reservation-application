package com.cinema.application.reservation.commands;

import com.cinema.application.common.Command;
import java.util.List;
import java.util.UUID;

// The input data required to create a reservation.
public record CreateReservationCommand(
        UUID userId,
        UUID sessionId,
        List<UUID> seatIds
) implements Command<UUID> {}