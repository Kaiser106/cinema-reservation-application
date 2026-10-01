package com.cinema.application.movie.queries;

import com.cinema.application.common.Query;
import java.util.List;

public record GetMoviesQuery() implements Query<List<Object>> {}