package ru.mityushin.jobfinder.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mityushin.jobfinder.server.service.http.HttpService;
import ru.mityushin.jobfinder.server.service.publication.PublicationService;
import ru.mityushin.jobfinder.server.util.dto.PublicationDTO;
import ru.mityushin.jobfinder.server.util.exception.data.DataCreateException;
import ru.mityushin.jobfinder.server.util.exception.data.DataDeleteException;
import ru.mityushin.jobfinder.server.util.exception.data.DataUpdateException;

import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/publications")
public class PublicationController {

    private PublicationService publicationService;
    private HttpService httpService;

    public PublicationController(PublicationService publicationService, HttpService httpService) {
        this.publicationService = publicationService;
        this.httpService = httpService;
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<PublicationDTO>> getPublications() {
        return new ResponseEntity<>(publicationService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/deleted")
    @ResponseBody
    public ResponseEntity<List<PublicationDTO>> getDeletedPublications() {
        return new ResponseEntity<>(publicationService.findAllDeleted(), HttpStatus.OK);
    }

    @GetMapping(value = "/{url}")
    @ResponseBody
    public ResponseEntity<PublicationDTO> getPublication(@PathVariable("url") String url) {

        PublicationDTO publication = publicationService.findByUrl(url);

        if (publication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createPublication(@RequestBody Map<String, String> body) {

        PublicationDTO publication = PublicationDTO.builder()
                .title(body.getOrDefault("title", null))
                .description(body.getOrDefault("description", null))
                .content(body.getOrDefault("content", null))
                .build();

        try {
            publication = publicationService.create(publication);
        } catch (DataCreateException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(publication, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{url}")
    @ResponseBody
    public ResponseEntity<?> updatePublication(@RequestBody Map<String, String> body, @PathVariable("url") String url) {

        PublicationDTO publication = PublicationDTO.builder()
                .uuid(body.getOrDefault("uuid", null))
                .url(url)
                .title(body.getOrDefault("title", null))
                .description(body.getOrDefault("description", null))
                .content(body.getOrDefault("content", null))
                .visible(body.getOrDefault("visible", null))
                .build();

        try {
            publication = publicationService.update(publication);
        } catch (DataUpdateException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{url}")
    @ResponseBody
    public ResponseEntity<?> deletePublication(@PathVariable("url") String url) {
        PublicationDTO publication = PublicationDTO.builder()
                .url(url)
                .build();
        try {
            publication = publicationService.delete(publication);
        } catch (DataDeleteException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

}
