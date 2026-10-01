package com.cinema.application.contracts.user;

import java.util.UUID;

// WHY: Other modules (like Reservation) need to verify users, but they must NOT access
// the UserRepository or UserEntity directly. This contract isolates the User module.
public interface UserContract {
    boolean existsById(UUID userId);
    UserDto getUserById(UUID userId);
}