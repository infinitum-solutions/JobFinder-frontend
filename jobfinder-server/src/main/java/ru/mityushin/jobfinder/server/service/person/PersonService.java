package ru.mityushin.jobfinder.server.service.person;

import ru.mityushin.jobfinder.server.util.dto.PersonDTO;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    List<PersonDTO> findAll();

    PersonDTO find(UUID uuid);

    PersonDTO create(PersonDTO personDTO);
    PersonDTO update(UUID uuid, PersonDTO personDTO);
    PersonDTO delete(UUID uuid);
}
