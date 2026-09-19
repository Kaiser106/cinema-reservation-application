package com.cinema.domain.movie;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Session {
    private UUID id;
    private UUID movieId;
    private UUID hallId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal price;

    public Session(UUID id, UUID movieId, UUID hallId, LocalDateTime startTime, LocalDateTime endTime, BigDecimal price) {
        this.id = id;
        this.movieId = movieId;
        this.hallId = hallId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public UUID getId() { return id; }
    public UUID getMovieId() { return movieId; }
    public UUID getHallId() { return hallId; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public BigDecimal getPrice() { return price; }
}