package com.cinema.application.point.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserPointDto {
    private UUID userId;
    private Integer balance;
}