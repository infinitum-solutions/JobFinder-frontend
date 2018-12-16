package ru.mityushin.jobfinder.server.service.person;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.mityushin.jobfinder.server.model.Person;
import ru.mityushin.jobfinder.server.repo.PersonRepository;
import ru.mityushin.jobfinder.server.util.dto.PersonDTO;
import ru.mityushin.jobfinder.server.util.exception.data.NotFoundDataException;
import ru.mityushin.jobfinder.server.util.mapper.PersonMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Override
    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream()
                .filter(o -> !o.getDeleted())
                .map(PersonMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO find(UUID uuid) {
        Person person = personRepository.findByUuid(uuid);
        if (person == null
                || person.getDeleted()) {
            throw new NotFoundDataException();
        }
        return PersonMapper.map(person);
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) {
        Person person = PersonMapper.map(personDTO);
        person.setUuid(UUID.randomUUID());
        person.setDeleted(Boolean.FALSE);
        person.setLocked(Boolean.FALSE);
        person.setEnabled(Boolean.TRUE);

        Person saved = personRepository.save(person);
        return PersonMapper.map(saved);
    }

    @Override
    public PersonDTO update(UUID uuid, PersonDTO personDTO) {

        Person personFromRepo = personRepository.findByUuid(uuid);
        if (isInaccessible(personFromRepo)) {
            throw new NotFoundDataException();
        }
        Person person = PersonMapper.map(personDTO);
        person.setId(personFromRepo.getId());
        person.setUuid(uuid);
        person.setDeleted(personFromRepo.getDeleted());
        person.setLocked(personFromRepo.getLocked());
        person.setEnabled(personFromRepo.getEnabled());
        person.setExpire(personFromRepo.getExpire());
        person.setCredentialsExpire(personFromRepo.getCredentialsExpire());
        Person saved = personRepository.save(person);
        return PersonMapper.map(saved);
    }

    @Override
    public PersonDTO delete(UUID uuid) {
        Person person = personRepository.findByUuid(uuid);
        if (isInaccessible(person)) {
            throw new NotFoundDataException();
        }
        person.setDeleted(Boolean.TRUE);
        return PersonMapper.map(personRepository.save(person));
    }

    private static boolean isInaccessible(@Nullable Person person) {
        return person == null
                || person.getDeleted()
                || person.getLocked()
                || !person.getEnabled();
    }
}
