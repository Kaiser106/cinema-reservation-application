package com.cinema.application.contracts.user;

import java.util.UUID;

// Using Java Records for immutable data transfer without Lombok.
public record UserDto(UUID id, String email, String role) {}