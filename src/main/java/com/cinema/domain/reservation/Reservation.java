package com.cinema.domain.reservation;

import com.cinema.domain.common.AggregateRoot;
import com.cinema.domain.common.Money;
import com.cinema.domain.reservation.events.ReservationCreatedEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

// WHY: Reservation is the main transactional boundary (Aggregate Root).
// Any operation regarding reserving seats must go through this entity.
// Notice the absence of Lombok. We use explicit constructors and methods to protect the business invariants.
public class Reservation extends AggregateRoot {

    private final UUID id;
    private final UUID userId;
    private final UUID sessionId;
    private ReservationStatus status;
    private Money totalPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiresAt;

    private final List<ReservationSeat> reservedSeats;

    public Reservation(UUID id, UUID userId, UUID sessionId, LocalDateTime now) {
        if (id == null || userId == null || sessionId == null) {
            throw new IllegalArgumentException("Reservation identifiers cannot be null");
        }
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.status = ReservationStatus.PENDING;
        this.totalPrice = Money.ZERO;
        this.createdAt = now;
        this.expiresAt = now.plusMinutes(15); // Business Rule: expires in 15 mins
        this.reservedSeats = new ArrayList<>();

        registerEvent(new ReservationCreatedEvent(this.id, this.userId));
    }

    // WHY: Reconstitution constructor. Used by repositories when mapping DB entity to Domain entity.
    public Reservation(UUID id, UUID userId, UUID sessionId, ReservationStatus status,
                       Money totalPrice, LocalDateTime createdAt, LocalDateTime expiresAt,
                       List<ReservationSeat> reservedSeats) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.reservedSeats = new ArrayList<>(reservedSeats);
    }

    public void addSeat(UUID seatId, Money seatPrice) {
        // Validation logic
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Cannot add seats to a reservation that is not PENDING");
        }

        // Ensure no duplicate seats inside the same reservation
        boolean alreadyExists = reservedSeats.stream()
                .anyMatch(rs -> rs.getSeatId().equals(seatId));

        if (alreadyExists) {
            throw new IllegalArgumentException("Seat is already added to this reservation");
        }

        ReservationSeat seat = new ReservationSeat(UUID.randomUUID(), this.id, this.sessionId, seatId, seatPrice);
        this.reservedSeats.add(seat);
        this.totalPrice = this.totalPrice.add(seatPrice);
    }

    public void confirm() {
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Only PENDING reservations can be confirmed.");
        }
        if (LocalDateTime.now().isAfter(this.expiresAt)) {
            throw new IllegalStateException("Reservation has expired.");
        }
        this.status = ReservationStatus.CONFIRMED;
        // DomainEvent for Confirmation could be registered here
    }

    public void cancel() {
        if (this.status == ReservationStatus.COMPLETED || this.status == ReservationStatus.EXPIRED) {
            throw new IllegalStateException("Cannot cancel completed or expired reservation");
        }
        this.status = ReservationStatus.CANCELLED;
    }

    public void expire() {
        if (this.status == ReservationStatus.PENDING) {
            this.status = ReservationStatus.EXPIRED;
        }
    }

    // Getters for properties. Notice NO setters. State mutations happen through explicit behaviors.
    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public UUID getSessionId() { return sessionId; }
    public ReservationStatus getStatus() { return status; }
    public Money getTotalPrice() { return totalPrice; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }

    public List<ReservationSeat> getReservedSeats() {
        return Collections.unmodifiableList(reservedSeats);
    }
}