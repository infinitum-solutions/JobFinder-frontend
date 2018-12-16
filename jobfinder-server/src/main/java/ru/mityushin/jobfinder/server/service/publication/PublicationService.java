package ru.mityushin.jobfinder.server.service.publication;

import ru.mityushin.jobfinder.server.util.dto.PublicationDTO;

import java.util.List;
import java.util.UUID;

public interface PublicationService {

    List<PublicationDTO> findAll();

    PublicationDTO find(UUID uuid);

    PublicationDTO create(PublicationDTO publicationDTO);
    PublicationDTO update(UUID uuid, PublicationDTO publicationDTO);
    PublicationDTO delete(UUID uuid);
}
