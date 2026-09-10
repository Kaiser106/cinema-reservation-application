package com.cinema.domain.hall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Seat {
    private UUID id;
    private UUID hallId;
    private String rowLabel;
    private Integer seatNumber;
    private String type;
}