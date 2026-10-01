package com.cinema;

import org.springframework.boot.SpringApplication;

public class TestCinemaReservationAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(CinemaReservationAppApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
