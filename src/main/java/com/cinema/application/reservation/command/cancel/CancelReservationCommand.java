package com.cinema.application.reservation.command.cancel;

import com.cinema.application.common.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CancelReservationCommand implements Command<Void> {
    private final UUID reservationId;
}