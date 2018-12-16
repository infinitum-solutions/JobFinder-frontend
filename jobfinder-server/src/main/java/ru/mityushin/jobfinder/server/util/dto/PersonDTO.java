package ru.mityushin.jobfinder.server.util.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PersonDTO {

    private UUID uuid;
    private String username;
    private String password;
}
