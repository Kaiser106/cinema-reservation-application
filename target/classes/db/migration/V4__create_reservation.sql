CREATE TABLE reservation (
                             id UUID PRIMARY KEY,
                             user_id UUID NOT NULL REFERENCES users(id),
                             session_id UUID NOT NULL REFERENCES session(id),
                             status VARCHAR(50) NOT NULL,
                             total_price DECIMAL(10,2) NOT NULL,
                             created_at TIMESTAMP NOT NULL,
                             expires_at TIMESTAMP NOT NULL,
                             updated_at TIMESTAMP NOT NULL
);

CREATE TABLE reservation_seat (
                                  id UUID PRIMARY KEY,
                                  seat_id UUID NOT NULL REFERENCES seat(id),
                                  reservation_id UUID NOT NULL REFERENCES reservation(id) ON DELETE CASCADE,
                                  session_id UUID NOT NULL REFERENCES session(id),
                                  price DECIMAL(10,2) NOT NULL,
                                  created_at TIMESTAMP NOT NULL,
                                  updated_at TIMESTAMP NOT NULL,
                                  version BIGINT NOT NULL DEFAULT 0,
    -- THE MOST CRITICAL CONSTRAINT IN THIS SYSTEM:
    -- Guarantees the database itself blocks double booking at the lowest level.
                                  UNIQUE(session_id, seat_id)
);