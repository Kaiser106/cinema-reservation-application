package com.cinema.application.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class MovieDto {
    private UUID id;
    private String title;
    private Integer durationMinutes;
    private String genre;
    private String rating;
    private String posterUrl;
}