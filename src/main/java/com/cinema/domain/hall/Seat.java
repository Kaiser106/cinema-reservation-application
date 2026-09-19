package com.cinema.domain.hall;

import java.util.UUID;

public class Seat {
    private UUID id;
    private UUID hallId;
    private String rowLabel;
    private Integer seatNumber;
    private String type;

    public Seat(UUID id, UUID hallId, String rowLabel, Integer seatNumber, String type) {
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
    public String getType() { return type; }
}