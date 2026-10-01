package com.cinema.domain.movie;

import com.cinema.domain.common.AggregateRoot;
import com.cinema.domain.common.Money;
import java.time.LocalDateTime;
import java.util.UUID;

// WHY: Represents a specific screening of a movie.
// It is an aggregate root because sessions are independently queried and managed.
public class Session extends AggregateRoot {
    private final UUID id;
    private final UUID movieId;
    private final UUID hallId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Money price;

    public Session(UUID id, UUID movieId, UUID hallId, LocalDateTime startTime, LocalDateTime endTime, Money price) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
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
    public Money getPrice() { return price; }
}