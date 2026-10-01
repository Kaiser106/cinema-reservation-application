package com.cinema.application.movie.queries;

import com.cinema.application.common.Query;
import java.util.UUID;

// Return type will be a MovieDto (to be defined in Presentation or Application)
public record GetMovieQuery(UUID movieId) implements Query<Object> {}