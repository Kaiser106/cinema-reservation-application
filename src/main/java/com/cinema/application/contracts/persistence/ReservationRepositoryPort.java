package com.cinema.application.contracts.persistence;

import com.cinema.domain.reservation.Reservation;
import java.util.Optional;
import java.util.UUID;

// WHY: Abstraction over Spring Data JPA. Allows us to save Domain Aggregates directly.
public interface ReservationRepositoryPort {
    void save(Reservation reservation);
    Optional<Reservation> findById(UUID id);
}