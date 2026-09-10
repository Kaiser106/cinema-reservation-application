package com.cinema.domain.hall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Hall {
    private UUID id;
    private String name;
    private Integer rowColumn;
}