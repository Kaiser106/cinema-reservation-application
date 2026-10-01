package com.cinema.application.contracts.external;

public record PaymentResult(boolean isSuccess, String transactionReference, String errorMessage) {}