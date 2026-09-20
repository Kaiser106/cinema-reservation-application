package com.cinema.application.payment.command;

import com.cinema.application.common.Command;
import com.cinema.application.payment.dto.PaymentResultDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProcessPaymentCommand implements Command<PaymentResultDto> {
    private final UUID reservationId;
    private final String cardNumber;
    private final String expireMonth;
    private final String expireYear;
    private final String cvc;
}