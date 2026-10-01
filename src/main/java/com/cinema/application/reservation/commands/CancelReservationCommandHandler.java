package com.cinema.application.reservation.commands;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.persistence.OutboxPort;
import com.cinema.application.contracts.persistence.ReservationRepositoryPort;
import com.cinema.domain.reservation.Reservation;
import com.cinema.domain.reservation.events.ReservationCancelledEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CancelReservationCommandHandler implements CommandHandler<CancelReservationCommand, Void> {

    private final ReservationRepositoryPort reservationRepositoryPort;
    private final OutboxPort outboxPort;

    public CancelReservationCommandHandler(ReservationRepositoryPort reservationRepositoryPort, OutboxPort outboxPort) {
        this.reservationRepositoryPort = reservationRepositoryPort;
        this.outboxPort = outboxPort;
    }

    @Override
    @Transactional
    public Void handle(CancelReservationCommand command) {
        Reservation reservation = reservationRepositoryPort.findById(command.reservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.cancel();

        outboxPort.saveEvents(
                "Reservation",
                reservation.getId().toString(),
                List.of(new ReservationCancelledEvent(reservation.getId()))
        );

        reservationRepositoryPort.save(reservation);
        return null;
    }
}