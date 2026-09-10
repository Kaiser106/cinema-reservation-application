package com.cinema.domain.reservation;

import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Reservation {
    private UUID id;
    private UUID userId;
    private UUID sessionId;
    private ReservationStatus status;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    private List<ReservationSeat> seats;


    public Reservation(UUID userId, UUID sessionId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.sessionId = sessionId;
        this.status = ReservationStatus.PENDING;
        this.totalPrice = BigDecimal.ZERO;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = this.createdAt.plusMinutes(15);
        this.seats = new ArrayList<>();
    }

    public void addSeat(UUID seatId, BigDecimal seatPrice) {
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Sadece bekleyen rezervasyonlara koltuk eklenebilir.");
        }

        ReservationSeat newSeat = new ReservationSeat(
                UUID.randomUUID(), seatId, this.id, this.sessionId, seatPrice
        );
        this.seats.add(newSeat);
        this.totalPrice = this.totalPrice.add(seatPrice);
    }

    public void confirm() {
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Sadece bekleyen rezervasyonlar onaylanabilir.");
        }
        this.status = ReservationStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }
}