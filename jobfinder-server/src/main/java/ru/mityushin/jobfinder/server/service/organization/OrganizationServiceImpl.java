package ru.mityushin.jobfinder.server.service.organization;

import org.springframework.stereotype.Service;
import ru.mityushin.jobfinder.server.repo.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
}
