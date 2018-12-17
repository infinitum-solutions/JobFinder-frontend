package ru.mityushin.jobfinder.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mityushin.jobfinder.server.service.person.PersonService;
import ru.mityushin.jobfinder.server.util.dto.PersonDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/persons")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PersonDTO>> getPersons() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.create(personDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{uuid}")
    @ResponseBody
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("uuid") UUID uuid) {
        return new ResponseEntity<>(personService.find(uuid), HttpStatus.OK);
    }

    @PutMapping(value = "/{uuid}")
    @ResponseBody
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable(value = "uuid") UUID uuid,
                                                  @Valid @RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.update(uuid, personDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    @ResponseBody
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable(value = "uuid") UUID uuid) {
        return new ResponseEntity<>(personService.delete(uuid), HttpStatus.OK);
    }
}
