package com.cinema.domain.movie;

import java.util.UUID;

public class Movie {
    private UUID id;
    private String title;
    private Integer durationMinutes;
    private String genre;
    private String rating;
    private String posterUrl;

    public Movie(UUID id, String title, Integer durationMinutes, String genre, String rating, String posterUrl) {
        this.id = id;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.genre = genre;
        this.rating = rating;
        this.posterUrl = posterUrl;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public String getGenre() { return genre; }
    public String getRating() { return rating; }
    public String getPosterUrl() { return posterUrl; }
}