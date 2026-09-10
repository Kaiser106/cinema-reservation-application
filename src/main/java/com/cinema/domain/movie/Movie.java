package com.cinema.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Movie {
    private UUID id;
    private String title;
    private Integer durationMinutes;
    private String genre;
    private String rating; // Opsiyonel
    private String posterUrl; // Opsiyonel
}