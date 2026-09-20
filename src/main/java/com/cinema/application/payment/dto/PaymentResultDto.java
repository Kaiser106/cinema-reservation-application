package com.cinema.application.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class PaymentResultDto {
    private UUID paymentId;
    private String status;
    private String transactionRef;
    private LocalDateTime paidAt;
}