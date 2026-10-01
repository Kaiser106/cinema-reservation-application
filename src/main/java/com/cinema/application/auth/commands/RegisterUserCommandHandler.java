package com.cinema.application.auth.commands;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.persistence.CustomerRepositoryPort;
import com.cinema.application.contracts.persistence.UserRepositoryPort;
import com.cinema.domain.customer.Customer;
import com.cinema.domain.user.User;
import com.cinema.domain.user.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegisterUserCommandHandler implements CommandHandler<RegisterUserCommand, UUID> {

    // Dependencies will be implemented in Infrastructure
    private final UserRepositoryPort userRepositoryPort;
    private final CustomerRepositoryPort customerRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserCommandHandler(UserRepositoryPort userRepositoryPort,
                                      CustomerRepositoryPort customerRepositoryPort,
                                      PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.customerRepositoryPort = customerRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UUID handle(RegisterUserCommand command) {
        if (userRepositoryPort.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        UUID userId = UUID.randomUUID();
        String encodedPassword = passwordEncoder.encode(command.password());

        User user = new User(userId, command.email(), encodedPassword, UserRole.CUSTOMER);
        userRepositoryPort.save(user);

        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, userId, command.firstName(), command.lastName(), command.phone());
        customerRepositoryPort.save(customer);

        return userId;
    }
}