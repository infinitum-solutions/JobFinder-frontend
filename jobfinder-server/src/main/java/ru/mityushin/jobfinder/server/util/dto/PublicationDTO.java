package ru.mityushin.jobfinder.server.util.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
public class PublicationDTO {

    private UUID uuid;
    private String title;
    private String description;
    private String content;
    private Boolean visible;
    private Boolean deleted;
}
