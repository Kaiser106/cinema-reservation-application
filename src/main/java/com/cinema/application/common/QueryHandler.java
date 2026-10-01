package com.cinema.application.common;

// WHY: Ensures every query is handled by a specific class, keeping read operations isolated.
public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}