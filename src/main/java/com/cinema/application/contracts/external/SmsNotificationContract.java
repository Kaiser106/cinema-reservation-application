package com.cinema.application.contracts.external;

public interface SmsNotificationContract {
    void sendSms(String phoneNumber, String message);
}