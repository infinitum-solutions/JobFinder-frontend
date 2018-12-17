package ru.mityushin.jobfinder.server.service.organization;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.mityushin.jobfinder.server.model.Organization;
import ru.mityushin.jobfinder.server.model.Person;
import ru.mityushin.jobfinder.server.repo.OrganizationRepository;
import ru.mityushin.jobfinder.server.repo.PersonRepository;
import ru.mityushin.jobfinder.server.util.JobFinderUtils;
import ru.mityushin.jobfinder.server.util.dto.OrganizationDTO;
import ru.mityushin.jobfinder.server.util.exception.data.DataAlreadyExistsException;
import ru.mityushin.jobfinder.server.util.exception.data.DataNotFoundException;
import ru.mityushin.jobfinder.server.util.exception.data.MissingRequiredParametersException;
import ru.mityushin.jobfinder.server.util.mapper.OrganizationMapper;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;
    private PersonRepository personRepository;
    private Logger log;

    @Override
    public List<OrganizationDTO> findAll() {
        return organizationRepository.findAll().stream()
                .filter((Organization o) -> !o.getDeleted())
                .map(OrganizationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDTO find(UUID uuid) {
        Organization organization = organizationRepository.findByUuid(uuid);
        if (organization == null
                || organization.getDeleted()) {
            throw new DataNotFoundException("This organization has been deleted or has not been created yet.");
        }
        return OrganizationMapper.map(organization);
    }

    @Override
    public OrganizationDTO create(OrganizationDTO organizationDTO) {
        if (organizationDTO.getTitle() == null) {
            throw new MissingRequiredParametersException("Required parameter 'title' doesn't specified.");
        }
        Organization organization = OrganizationMapper.map(organizationDTO);
        organization.setUuid(UUID.randomUUID());
        organization.setCreatorUuid(JobFinderUtils.getPrincipalIdentifier());
        organization.setDeleted(Boolean.FALSE);
        organization.setSubscribers(new HashSet<>());
        return OrganizationMapper.map(organizationRepository.save(organization));
    }

    @Override
    public OrganizationDTO update(UUID uuid, OrganizationDTO organizationDTO) {
        Organization organizationFromRepo = organizationRepository.findByUuid(uuid);
        if (isInaccessible(organizationFromRepo)) {
            throw new DataNotFoundException("This organization has been deleted or has not been created yet.");
        }
        Organization prepared = mergeOrganizationDto(organizationFromRepo, organizationDTO);
        return OrganizationMapper.map(organizationRepository.save(prepared));
    }

    @Override
    public OrganizationDTO delete(UUID uuid) {
        Organization organization = organizationRepository.findByUuid(uuid);
        if (isInaccessible(organization)) {
            throw new DataNotFoundException("This organization has been deleted or has not been created yet.");
        }
        organization.setDeleted(Boolean.TRUE);
        return OrganizationMapper.map(organizationRepository.save(organization));
    }

    @Override
    public OrganizationDTO subscribe(UUID uuid) {
        Organization organization = organizationRepository.findByUuid(uuid);
        if (isInaccessible(organization)) {
            throw new DataNotFoundException("This organization has been deleted or has not been created yet.");
        }
        Person currentPerson = getCurrentPerson();
        log.info("CURRENT: " + currentPerson);
        boolean added = organization.getSubscribers().add(currentPerson);
        if (!added) {
            throw new DataAlreadyExistsException("You has been subscribed to this organization yet.");
        }
        return OrganizationMapper.map(organizationRepository.save(organization));
    }

    @Override
    public OrganizationDTO unsubscribe(UUID uuid) {
        Organization organization = organizationRepository.findByUuid(uuid);
        if (isInaccessible(organization)) {
            throw new DataNotFoundException("This organization has been deleted or has not been created yet.");
        }
        boolean removed = organization.getSubscribers().remove(getCurrentPerson());
        if (!removed) {
            throw new DataNotFoundException("You hasn't been subscribed to this organization yet.");
        }
        return OrganizationMapper.map(organizationRepository.save(organization));
    }

    private Person getCurrentPerson() {
        return personRepository.findByUuid(JobFinderUtils.getPrincipalIdentifier());
    }

    private static boolean isInaccessible(@Nullable Organization organization) {
        return organization == null
                || organization.getDeleted();
    }

    private static Organization mergeOrganizationDto(@NotNull Organization organization,
                                                     @NotNull OrganizationDTO organizationDTO) {
        return Organization.builder()
                .id(organization.getId())
                .uuid(organization.getUuid())
                .creatorUuid(organization.getCreatorUuid())
                .deleted(organization.getDeleted())
                .title(organizationDTO.getTitle())
                .description(organizationDTO.getDescription())
                .build();
    }
}