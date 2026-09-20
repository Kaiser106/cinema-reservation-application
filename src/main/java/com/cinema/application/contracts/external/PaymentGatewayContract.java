package com.cinema.application.contracts.external;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentGatewayContract {
    // Iyzipay entegrasyonu için sözleşme
    boolean processPayment(UUID reservationId, BigDecimal amount, String cardNumber, String expireMonth, String expireYear, String cvc);
}