package com.cinema.application.auth.commands;

import com.cinema.application.common.Command;

public record LoginUserCommand(String email, String password) implements Command<String> {} // Returns session token