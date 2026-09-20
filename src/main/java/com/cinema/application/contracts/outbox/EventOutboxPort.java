package com.cinema.application.contracts.outbox;

import com.cinema.domain.common.DomainEvent;

public interface EventOutboxPort {
    void save(DomainEvent event);
}