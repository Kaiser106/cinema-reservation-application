package com.cinema.application.reservation.command.create;

import com.cinema.application.common.Command;
import com.cinema.application.reservation.dto.ReservationResultDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateReservationCommand implements Command<ReservationResultDto> {
    private final UUID userId;
    private final UUID sessionId;
    private final List<UUID> seatIds;
}