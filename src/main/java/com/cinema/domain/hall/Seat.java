package com.cinema.domain.hall;

import java.util.UUID;

// WHY: Seat belongs inside the Hall aggregate conceptually, but since it is heavily referenced
// by the Reservation system, having it independently identifiable via UUID is necessary for decoupled references.
public class Seat {
    private final UUID id;
    private final UUID hallId;
    private final String rowLabel;
    private final Integer seatNumber; // Integer is best practice for sequential business numbers
    private final SeatType type;

    public Seat(UUID id, UUID hallId, String rowLabel, Integer seatNumber, SeatType type) {
        this.id = id;
        this.hallId = hallId;
        this.rowLabel = rowLabel;
        this.seatNumber = seatNumber;
        this.type = type;
    }

    public UUID getId() { return id; }
    public UUID getHallId() { return hallId; }
    public String getRowLabel() { return rowLabel; }
    public Integer getSeatNumber() { return seatNumber; }
    public SeatType getType() { return type; }
}