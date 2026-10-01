package com.cinema.application.auth.commands;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.persistence.UserRepositoryPort;
import com.cinema.application.contracts.persistence.UserSessionRepositoryPort;
import com.cinema.domain.user.User;
import com.cinema.domain.user.UserSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class LoginUserCommandHandler implements CommandHandler<LoginUserCommand, String> {

    private final UserRepositoryPort userRepositoryPort;
    private final UserSessionRepositoryPort userSessionRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public LoginUserCommandHandler(UserRepositoryPort userRepositoryPort,
                                   UserSessionRepositoryPort userSessionRepositoryPort,
                                   PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.userSessionRepositoryPort = userSessionRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public String handle(LoginUserCommand command) {
        User user = userRepositoryPort.findByEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(command.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // Generate a secure random token for the user to keep in their cookie/client
        String rawToken = UUID.randomUUID().toString() + "-" + UUID.randomUUID().toString();

        // Never store the raw token in the database. Store a hash.
        String tokenHash = hashToken(rawToken);

        UserSession session = new UserSession(
                UUID.randomUUID(),
                user.getId(),
                tokenHash,
                LocalDateTime.now().plusHours(24) // Session valid for 24 hours
        );

        userSessionRepositoryPort.save(session);

        // Return the RAW token to the client. The database only knows the hash.
        return rawToken;
    }

    private String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing session token", e);
        }
    }
}