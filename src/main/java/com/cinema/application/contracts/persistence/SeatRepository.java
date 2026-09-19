package com.cinema.application.contracts.persistence;

import com.cinema.domain.hall.Seat;
import java.util.List;
import java.util.UUID;

public interface SeatRepository {
    List<Seat> findAllByIdIn(List<UUID> seatIds);
}