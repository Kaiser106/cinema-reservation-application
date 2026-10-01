package com.cinema.application.reservation.commands;

import com.cinema.application.common.Command;
import java.util.UUID;

public record CancelReservationCommand(UUID reservationId) implements Command<Void> {}