package com.cinema.application.contracts.persistence;

import com.cinema.domain.customer.Customer;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepositoryPort {
    void save(Customer customer);
    Optional<Customer> findByUserId(UUID userId);
}