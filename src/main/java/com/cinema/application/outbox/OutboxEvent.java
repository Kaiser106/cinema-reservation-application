package com.cinema.application.outbox;

import java.time.LocalDateTime;
import java.util.UUID;

// WHY: Represents the event to be stored in the database within the same transaction.
public class OutboxEvent {
    private final UUID id;
    private final String eventType;
    private final String aggregateType;
    private final String aggregateId;
    private final String payload;
    private String status;
    private final LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private int retryCount;

    public OutboxEvent(UUID id, String eventType, String aggregateType, String aggregateId, String payload) {
        this.id = id;
        this.eventType = eventType;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.payload = payload;
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
        this.retryCount = 0;
    }

    public void markAsProcessed() {
        this.status = "PROCESSED";
        this.processedAt = LocalDateTime.now();
    }

    public void markAsFailed() {
        this.status = "FAILED";
    }

    public void incrementRetry() {
        this.retryCount++;
    }

    public UUID getId() { return id; }
    public String getEventType() { return eventType; }
    public String getAggregateType() { return aggregateType; }
    public String getAggregateId() { return aggregateId; }
    public String getPayload() { return payload; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getProcessedAt() { return processedAt; }
    public int getRetryCount() { return retryCount; }
}