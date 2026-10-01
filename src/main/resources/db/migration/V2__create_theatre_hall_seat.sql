CREATE TABLE theatre_info (
                              id UUID PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              address TEXT,
                              phone VARCHAR(20),
                              created_at TIMESTAMP NOT NULL,
                              updated_at TIMESTAMP NOT NULL
);

CREATE TABLE hall (
                      id UUID PRIMARY KEY,
                      theatre_id UUID NOT NULL REFERENCES theatre_info(id),
                      name VARCHAR(100) NOT NULL,
                      row_count INT NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      updated_at TIMESTAMP NOT NULL
);

CREATE TABLE seat (
                      id UUID PRIMARY KEY,
                      hall_id UUID NOT NULL REFERENCES hall(id),
                      row_label VARCHAR(10) NOT NULL,
                      seat_number INT NOT NULL, -- Sequential identifier appropriate as INT
                      type VARCHAR(50) NOT NULL,
    -- CRITICAL CONSTRAINT: A specific seat in a hall is mathematically unique
                      UNIQUE(hall_id, row_label, seat_number)
);