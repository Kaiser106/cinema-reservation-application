package com.cinema.application.contracts.customer;

import java.util.Optional;
import java.util.UUID;

// WHY: Isolates the Customer module. If the Payment or Reservation module needs customer details
// (e.g., to send an SMS or generate an invoice), they MUST use this contract.
public interface CustomerContract {
    Optional<CustomerDto> getCustomerByUserId(UUID userId);
    boolean existsByUserId(UUID userId);
}