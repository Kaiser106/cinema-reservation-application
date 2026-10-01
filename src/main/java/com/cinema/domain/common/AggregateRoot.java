package com.cinema.domain.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// WHY: Every aggregate root should be able to register domain events.
// This allows the Domain layer to signal business events (like ReservationConfirmed)
// to the Application layer without needing a message broker explicitly inside the Domain.
public abstract class AggregateRoot {

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected void registerEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}