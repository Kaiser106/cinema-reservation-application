package com.cinema.domain.customer;

import com.cinema.domain.common.AggregateRoot;
import java.util.UUID;

// WHY: The Customer aggregate contains business-specific information of the person making reservations.
// It keeps a reference (userId) to the User aggregate but they are decoupled in the Domain layer.
public class Customer extends AggregateRoot {
    private final UUID id;
    private final UUID userId;
    private String firstName;
    private String lastName;
    private String phone;

    public Customer(UUID id, UUID userId, String firstName, String lastName, String phone) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public void updateProfile(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
}