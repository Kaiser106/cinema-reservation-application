-- Indexes added based on actual query patterns for performance optimization
CREATE INDEX idx_reservation_user ON reservation(user_id);
CREATE INDEX idx_reservation_session ON reservation(session_id);
CREATE INDEX idx_reservation_status ON reservation(status);
CREATE INDEX idx_reservation_created ON reservation(created_at);

CREATE INDEX idx_session_movie ON session(movie_id);
CREATE INDEX idx_session_hall ON session(hall_id);
CREATE INDEX idx_session_start_time ON session(start_time);
CREATE INDEX idx_session_movie_start ON session(movie_id, start_time);

CREATE INDEX idx_seat_hall ON seat(hall_id);

CREATE INDEX idx_payment_reservation ON payment(reservation_id);
CREATE INDEX idx_users_email ON users(email);

CREATE INDEX idx_user_session_user ON user_session(user_id);
CREATE INDEX idx_user_session_expires ON user_session(expires_at);
CREATE INDEX idx_user_session_token ON user_session(session_token_hash);