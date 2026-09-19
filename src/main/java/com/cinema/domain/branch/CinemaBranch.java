package com.cinema.domain.branch;

import java.util.UUID;

public class CinemaBranch {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;

    public CinemaBranch(UUID id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
}