package ru.mityushin.jobfinder.server.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class OrganizationDTO {
    private UUID uuid;
    private UUID creatorUuid;
    private String title;
    private String description;
    private Integer subscribersCount;
}
