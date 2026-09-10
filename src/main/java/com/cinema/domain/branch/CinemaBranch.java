package com.cinema.domain.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CinemaBranch {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
}