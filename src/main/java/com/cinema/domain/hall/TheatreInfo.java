package com.cinema.domain.hall;

import com.cinema.domain.common.AggregateRoot;
import java.util.UUID;

// WHY: Represents the physical building/location that contains multiple Halls.
public class TheatreInfo extends AggregateRoot {
    private final UUID id;
    private String name;
    private String address;
    private String phone;

    public TheatreInfo(UUID id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}