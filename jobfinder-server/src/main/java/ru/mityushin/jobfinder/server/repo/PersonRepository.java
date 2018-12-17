package ru.mityushin.jobfinder.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mityushin.jobfinder.server.model.Person;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findAll();
    Person findByUuid(UUID uuid);
    Person findByUsername(String username);
    boolean existsByUsername(String username);
}
