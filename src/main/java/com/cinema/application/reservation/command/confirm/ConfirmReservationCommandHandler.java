package com.cinema.application.reservation.command.confirm;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.persistence.ReservationRepository;
import com.cinema.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConfirmReservationCommandHandler implements CommandHandler<ConfirmReservationCommand, Void> {

    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Void handle(ConfirmReservationCommand command) {
        Reservation reservation = reservationRepository.findById(command.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("Rezervasyon bulunamadı."));

        reservation.confirm();
        reservationRepository.save(reservation);

        return null;
    }
}