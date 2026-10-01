package com.cinema.application.contracts.persistence;

import com.cinema.domain.common.DomainEvent;
import java.util.List;

// WHY: Port to save domain events to the outbox table within the SAME transaction.
public interface OutboxPort {
    void saveEvents(String aggregateType, String aggregateId, List<DomainEvent> events);
}