package com.cinema.application.auth.commands;

import com.cinema.application.common.CommandHandler;
import com.cinema.application.contracts.persistence.UserSessionRepositoryPort;
import com.cinema.domain.user.UserSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogoutUserCommandHandler implements CommandHandler<LogoutUserCommand, Void> {

    private final UserSessionRepositoryPort userSessionRepositoryPort;

    public LogoutUserCommandHandler(UserSessionRepositoryPort userSessionRepositoryPort) {
        this.userSessionRepositoryPort = userSessionRepositoryPort;
    }

    @Override
    @Transactional
    public Void handle(LogoutUserCommand command) {
        // We look up the session by its hash, because the raw token is never stored.
        UserSession session = userSessionRepositoryPort.findByTokenHash(command.sessionToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid or already revoked session"));

        session.revoke();
        userSessionRepositoryPort.save(session);
        return null;
    }
}