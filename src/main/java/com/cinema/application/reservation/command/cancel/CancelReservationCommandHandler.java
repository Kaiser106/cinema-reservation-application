package com.cinema.application.reservation.command.cancel;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.persistence.ReservationRepository;
import com.cinema.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelReservationCommandHandler implements CommandHandler<CancelReservationCommand, Void> {

    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Void handle(CancelReservationCommand command) {
        Reservation reservation = reservationRepository.findById(command.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("Rezervasyon bulunamadı."));

        reservation.cancel();
        reservationRepository.save(reservation);

        return null;
    }
}