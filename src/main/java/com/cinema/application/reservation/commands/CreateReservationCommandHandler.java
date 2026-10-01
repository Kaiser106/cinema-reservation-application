package com.cinema.application.reservation.commands;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.persistence.OutboxPort;
import com.cinema.application.contracts.persistence.ReservationRepositoryPort;
import com.cinema.application.contracts.persistence.SessionRepositoryPort;
import com.cinema.application.contracts.user.UserContract;
import com.cinema.domain.movie.Session;
import com.cinema.domain.reservation.Reservation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateReservationCommandHandler implements CommandHandler<CreateReservationCommand, UUID> {

    private final UserContract userContract;
    private final SessionRepositoryPort sessionRepositoryPort;
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final OutboxPort outboxPort;

    public CreateReservationCommandHandler(
            UserContract userContract,
            SessionRepositoryPort sessionRepositoryPort,
            ReservationRepositoryPort reservationRepositoryPort,
            OutboxPort outboxPort) {
        this.userContract = userContract;
        this.sessionRepositoryPort = sessionRepositoryPort;
        this.reservationRepositoryPort = reservationRepositoryPort;
        this.outboxPort = outboxPort;
    }

    @Override
    @Transactional
    public UUID handle(CreateReservationCommand command) {
        if (!userContract.existsById(command.userId())) {
            throw new IllegalArgumentException("User does not exist");
        }

        Session session = sessionRepositoryPort.findById(command.sessionId())
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        Reservation reservation = new Reservation(
                UUID.randomUUID(),
                command.userId(),
                command.sessionId(),
                LocalDateTime.now()
        );

        for (UUID seatId : command.seatIds()) {
            reservation.addSeat(seatId, session.getPrice());
        }

        reservationRepositoryPort.save(reservation);

        outboxPort.saveEvents(
                "Reservation",
                reservation.getId().toString(),
                reservation.getDomainEvents()
        );

        reservation.clearDomainEvents();

        return reservation.getId();
    }
}