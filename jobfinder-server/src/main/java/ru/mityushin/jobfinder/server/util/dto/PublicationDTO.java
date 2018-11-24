package ru.mityushin.jobfinder.server.util.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PublicationDTO implements DTO {

    private final String uuid;

    private final String authorUUID;

    private final String organizationUUID;

    private final String url;

    private final String title;

    private final String description;

    private final String content;

    private final String visible;

    private final String deleted;
}
