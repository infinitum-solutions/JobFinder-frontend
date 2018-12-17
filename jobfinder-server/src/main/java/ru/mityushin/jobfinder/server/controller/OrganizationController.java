package ru.mityushin.jobfinder.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mityushin.jobfinder.server.service.organization.OrganizationService;
import ru.mityushin.jobfinder.server.util.dto.OrganizationDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/organizations")
@AllArgsConstructor
public class OrganizationController {
    private OrganizationService organizationService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<OrganizationDTO>> getOrganizations() {
        return new ResponseEntity<>(organizationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<OrganizationDTO> createPublication(@Valid @RequestBody OrganizationDTO organizationDTO) {
        return new ResponseEntity<>(organizationService.create(organizationDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{uuid}")
    @ResponseBody
    public ResponseEntity<OrganizationDTO> getPublication(@PathVariable("uuid") UUID uuid) {
        return new ResponseEntity<>(organizationService.find(uuid), HttpStatus.OK);
    }

    @PutMapping(value = "/{uuid}")
    @ResponseBody
    public ResponseEntity<OrganizationDTO> updatePublication(@PathVariable("uuid") UUID uuid,
                                               @Valid @RequestBody OrganizationDTO organizationDTO) {
        return new ResponseEntity<>(organizationService.update(uuid, organizationDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    @ResponseBody
    public ResponseEntity<OrganizationDTO> deletePublication(@PathVariable("uuid") UUID uuid) {
        return new ResponseEntity<>(organizationService.delete(uuid), HttpStatus.OK);
    }

    @PostMapping(value = "/{uuid}/subscribe")
    @ResponseBody
    public ResponseEntity<OrganizationDTO> subscribe(@PathVariable("uuid") UUID uuid) {
        return new ResponseEntity<>(organizationService.subscribe(uuid), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{uuid}/subscribe")
    @ResponseBody
    public ResponseEntity<OrganizationDTO> unsubscribe(@PathVariable("uuid") UUID uuid) {
        return new ResponseEntity<>(organizationService.unsubscribe(uuid), HttpStatus.OK);
    }
}
