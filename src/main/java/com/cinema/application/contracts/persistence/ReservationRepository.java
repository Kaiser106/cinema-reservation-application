package com.cinema.application.contracts.persistence;

import com.cinema.domain.reservation.Reservation;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(UUID id);
}