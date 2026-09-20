package com.cinema.application.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReservationResultDto {
    private UUID reservationId;
    private String status;
    private BigDecimal totalPrice;
    private LocalDateTime expiresAt;
}