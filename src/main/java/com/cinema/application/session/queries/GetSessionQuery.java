package com.cinema.application.session.queries;

import com.cinema.application.common.Query;
import java.util.UUID;

public record GetSessionQuery(UUID sessionId) implements Query<Object> {}