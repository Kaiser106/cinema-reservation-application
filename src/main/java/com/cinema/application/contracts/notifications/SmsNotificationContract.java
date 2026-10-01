package com.cinema.application.contracts.notification;

// WHY: Abstracts Twilio SMS sending. Will be called by OutboxProcessor, not directly in main logic.
public interface SmsNotificationContract {
    void sendSms(String phoneNumber, String message);
}