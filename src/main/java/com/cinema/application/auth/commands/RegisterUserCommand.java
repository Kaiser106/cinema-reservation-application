package com.cinema.application.auth.commands;

import com.cinema.application.common.Command;
import java.util.UUID;

public record RegisterUserCommand(
        String email,
        String password,
        String firstName,
        String lastName,
        String phone
) implements Command<UUID> {} // Returns new User ID