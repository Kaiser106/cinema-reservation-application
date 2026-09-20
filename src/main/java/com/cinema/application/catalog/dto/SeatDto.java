package com.cinema.application.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class SeatDto {
    private UUID id;
    private String rowLabel;
    private Integer seatNumber;
    private String type;
    private boolean isAvailable; // Query'ler çalışırken aktif rezervasyonlara bakılarak dinamik hesaplanır.
}