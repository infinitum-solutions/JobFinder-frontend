package ru.mityushin.jobfinder.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mityushin.jobfinder.server.service.http.HttpService;
import ru.mityushin.jobfinder.server.service.organization.OrganizationService;
import ru.mityushin.jobfinder.server.util.dto.OrganizationDTO;
import ru.mityushin.jobfinder.server.util.exception.data.DataCreateException;
import ru.mityushin.jobfinder.server.util.exception.data.DataUpdateException;

import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;
    private HttpService httpService;

    public OrganizationController(OrganizationService organizationService, HttpService httpService) {
        this.organizationService = organizationService;
        this.httpService = httpService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<OrganizationDTO>> getOrganizations() {
        return new ResponseEntity<>(organizationService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/deleted")
    @ResponseBody
    public ResponseEntity<List<OrganizationDTO>> getDeletedOrganizations() {
        return new ResponseEntity<>(organizationService.findAllDeleted(), HttpStatus.OK);
    }

    @GetMapping(value = "/{url}")
    @ResponseBody
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable("url") String url) {
        OrganizationDTO organization = organizationService.findByUrl(url);

        if (organization == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createOrganization(@RequestBody Map<String, String> body) {

        OrganizationDTO organization = OrganizationDTO.builder()
                .url(body.getOrDefault("url", null))
                .title(body.getOrDefault("title", null))
                .description(body.getOrDefault("description", null))
                .build();

        try {
            organization = organizationService.create(organization);
        } catch (DataCreateException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{url}")
    @ResponseBody
    public ResponseEntity<?> updateOrganization(@RequestBody Map<String, String> body, @PathVariable("url") String url) {

        OrganizationDTO organization = OrganizationDTO.builder()
                .uuid(body.getOrDefault("uuid", null))
                .url(url)
                .title(body.getOrDefault("title", null))
                .description(body.getOrDefault("description", null))
                .build();

        try {
            organization = organizationService.update(organization);
        } catch (DataUpdateException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{url}")
    @ResponseBody
    public ResponseEntity<?> deleteOrganization(@PathVariable("url") String url) {

        OrganizationDTO organization = OrganizationDTO.builder()
                .url(url)
                .build();

        try {
            organization = organizationService.delete(organization);
        } catch (DataUpdateException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

}
