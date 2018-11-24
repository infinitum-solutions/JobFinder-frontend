package ru.mityushin.jobfinder.server.util.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class OrganizationDTO implements DTO {

    private final String uuid;

    private final String ownerUUID;

    private final String url;

    private final String title;

    private final String description;

    private final Set<String> members;

    private final Set<String> subscribers;

    private final String deleted;
}
