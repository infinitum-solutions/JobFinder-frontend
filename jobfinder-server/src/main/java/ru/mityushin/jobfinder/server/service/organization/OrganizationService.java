package ru.mityushin.jobfinder.server.service.organization;

import ru.mityushin.jobfinder.server.util.dto.OrganizationDTO;
import ru.mityushin.jobfinder.server.util.dto.PersonDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface OrganizationService {
    List<OrganizationDTO> findAll();
    OrganizationDTO find(UUID uuid);
    OrganizationDTO create(OrganizationDTO organizationDTO);
    OrganizationDTO update(UUID uuid, OrganizationDTO organizationDTO);
    OrganizationDTO delete(UUID uuid);
    Set<PersonDTO> getSubscribers(UUID uuid);
    OrganizationDTO subscribe(UUID uuid);
    OrganizationDTO unsubscribe(UUID uuid);
}
