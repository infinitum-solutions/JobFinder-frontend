package ru.mityushin.jobfinder.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mityushin.jobfinder.server.model.Organization;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {
    List<Organization> findAll();
    Organization findByUuid(UUID uuid);
}
