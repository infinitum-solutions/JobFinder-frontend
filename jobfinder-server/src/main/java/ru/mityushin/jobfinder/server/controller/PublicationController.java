package ru.mityushin.jobfinder.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mityushin.jobfinder.server.service.publication.PublicationService;
import ru.mityushin.jobfinder.server.util.dto.PublicationDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/publications")
@AllArgsConstructor
public class PublicationController {

    private PublicationService publicationService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PublicationDTO>> getPublications() {
        return new ResponseEntity<>(publicationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createPublication(@Valid @RequestBody PublicationDTO publicationDTO) {
        return new ResponseEntity<>(publicationService.create(publicationDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{uuid}")
    @ResponseBody
    public ResponseEntity<PublicationDTO> getPublication(@PathVariable("uuid") UUID uuid) {

        PublicationDTO publication = publicationService.find(uuid);
        if (publication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @PutMapping(value = "/{uuid}")
    @ResponseBody
    public ResponseEntity<?> updatePublication(@PathVariable("uuid") UUID uuid,
                                               @Valid @RequestBody PublicationDTO publicationDTO) {
        return new ResponseEntity<>(publicationService.update(uuid, publicationDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    @ResponseBody
    public ResponseEntity<?> deletePublication(@PathVariable("uuid") UUID uuid) {
        return new ResponseEntity<>(publicationService.delete(uuid), HttpStatus.OK);
    }

}
