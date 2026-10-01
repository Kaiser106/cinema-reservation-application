package com.cinema.application.contracts.reservation;

import java.math.BigDecimal;
import java.util.UUID;

public record ReservationDto(UUID id, UUID userId, UUID sessionId, String status, BigDecimal totalPrice) {}