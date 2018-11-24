package ru.mityushin.jobfinder.server.util.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class UserInfoDTO implements DTO {

    private final String uuid;

    private final String username;

    private final String password;

    private final Set<String> roles;

    private final String url;

    private final String firstName;

    private final String secondName;

    private final String lastName;

    private final String age;

    private final String sex;

    private final Set<String> publications;

    private final Set<String> subscribedOrganizations;

    private final Set<String> memberOrganizations;
}
