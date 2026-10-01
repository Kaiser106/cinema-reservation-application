package com.cinema.domain.movie;

import com.cinema.domain.common.AggregateRoot;
import java.util.UUID;

public class Movie extends AggregateRoot {
    private final UUID id;
    private String title;
    private int durationMinutes; // Integer is enough for duration
    private String genre;
    private String rating;

    public Movie(UUID id, String title, int durationMinutes, String genre, String rating) {
        this.id = id;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.genre = genre;
        this.rating = rating;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public int getDurationMinutes() { return durationMinutes; }
    public String getGenre() { return genre; }
    public String getRating() { return rating; }
}