package com.cinema.application.common;

// WHY: Ensures every command is handled by a specific, single-purpose class.
public interface CommandHandler<C extends Command<R>, R> {
    R handle(C command);
}