package com.cinema.application.contracts.customer;

import java.util.UUID;

// Immutable DTO for cross-module data transfer.
public record CustomerDto(UUID id, UUID userId, String firstName, String lastName, String phone) {}