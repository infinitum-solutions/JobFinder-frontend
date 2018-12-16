package ru.mityushin.jobfinder.server.service.person;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mityushin.jobfinder.server.model.Person;
import ru.mityushin.jobfinder.server.repo.PersonRepository;
import ru.mityushin.jobfinder.server.util.dto.PersonDTO;
import ru.mityushin.jobfinder.server.util.exception.data.DataAlreadyExistsException;
import ru.mityushin.jobfinder.server.util.exception.data.DataNotFoundException;
import ru.mityushin.jobfinder.server.util.exception.data.MissingRequiredParametersException;
import ru.mityushin.jobfinder.server.util.mapper.PersonMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private PasswordEncoder encoder;

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
            throw new DataNotFoundException("This profile has been deleted or has not been created yet.");
        }
        return PersonMapper.map(person);
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) {
        if (personDTO.getUsername() == null) {
            throw new MissingRequiredParametersException("Required parameter 'username' doesn't specified.");
        }
        if (personDTO.getPassword() == null) {
            throw new MissingRequiredParametersException("Required parameter 'password' doesn't specified.");
        }
        if (personRepository.existsByUsername(personDTO.getUsername())) {
            throw new DataAlreadyExistsException("User with specified username already exists.");
        }
        Person person = PersonMapper.map(personDTO);
        person.setUuid(UUID.randomUUID());
        person.setPassword(encoder.encode(personDTO.getPassword()));
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
            throw new DataNotFoundException("This profile has been deleted or has not been created yet.");
        }
        Person prepared = mergePersonDtoAndEncodePassword(personFromRepo, personDTO, encoder);
        Person saved = personRepository.save(prepared);
        return PersonMapper.map(saved);
    }

    @Override
    public PersonDTO delete(UUID uuid) {
        Person person = personRepository.findByUuid(uuid);
        if (isInaccessible(person)) {
            throw new DataNotFoundException("This profile has been deleted or has not been created yet.");
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

    private static Person mergePersonDtoAndEncodePassword(Person person, PersonDTO personDTO, PasswordEncoder encoder) {
        return Person.builder()
                .id(person.getId())
                .uuid(person.getUuid())
                .username(personDTO.getUsername() == null ? person.getUsername() : personDTO.getUsername())
                .password(personDTO.getPassword() == null ? person.getPassword() : encoder.encode(personDTO.getPassword()))
                .deleted(person.getDeleted())
                .locked(person.getLocked())
                .enabled(person.getEnabled())
                .expire(person.getExpire())
                .credentialsExpire(person.getCredentialsExpire())
                .build();
    }
}
