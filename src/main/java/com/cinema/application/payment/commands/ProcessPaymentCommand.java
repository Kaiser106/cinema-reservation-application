package com.cinema.application.payment.commands;

import com.cinema.application.common.Command;
import java.util.UUID;

public record ProcessPaymentCommand(
        UUID reservationId,
        String paymentMethod,
        String idempotencyKey
) implements Command<Boolean> {}