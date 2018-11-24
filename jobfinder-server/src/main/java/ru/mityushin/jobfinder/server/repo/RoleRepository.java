package ru.mityushin.jobfinder.server.repo;

import org.springframework.data.repository.CrudRepository;
import ru.mityushin.jobfinder.server.model.Role;

import java.util.HashSet;

public interface RoleRepository extends CrudRepository<Role, Long> {
    HashSet<Role> findAll();
}
