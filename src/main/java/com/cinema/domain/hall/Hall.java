package com.cinema.domain.hall;

import java.util.UUID;

public class Hall {
    private UUID id;
    private String name;
    private Integer rowColumn;

    public Hall(UUID id, String name, Integer rowColumn) {
        this.id = id;
        this.name = name;
        this.rowColumn = rowColumn;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public Integer getRowColumn() { return rowColumn; }
}