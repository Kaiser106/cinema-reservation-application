package com.cinema.domain.common;

import java.time.LocalDateTime;

// WHY: Base contract for events happening inside the business logic.
// They are decoupled from specific infrastructure (no Spring ApplicationEvent here).
public interface DomainEvent {
    LocalDateTime getOccurredOn();
}