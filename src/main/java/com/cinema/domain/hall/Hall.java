package com.cinema.domain.hall;

import com.cinema.domain.common.AggregateRoot;
import java.util.UUID;

public class Hall extends AggregateRoot {
    private final UUID id;
    private final UUID theatreId;
    private final String name;
    private final int rowCount;

    public Hall(UUID id, UUID theatreId, String name, int rowCount) {
        this.id = id;
        this.theatreId = theatreId;
        this.name = name;
        this.rowCount = rowCount;
    }

    public UUID getId() { return id; }
    public UUID getTheatreId() { return theatreId; }
    public String getName() { return name; }
    public int getRowCount() { return rowCount; }
}