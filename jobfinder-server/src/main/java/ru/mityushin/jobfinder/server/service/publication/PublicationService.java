package ru.mityushin.jobfinder.server.service.publication;

import ru.mityushin.jobfinder.server.util.dto.PublicationDTO;
import ru.mityushin.jobfinder.server.util.exception.data.AlreadyExistsDataException;
import ru.mityushin.jobfinder.server.util.exception.data.NotFoundDataException;
import ru.mityushin.jobfinder.server.util.exception.data.RequiredParametersDataException;

import java.util.List;
import java.util.UUID;

public interface PublicationService {

    List<PublicationDTO> findAll();

    PublicationDTO find(UUID uuid) throws NotFoundDataException;

    PublicationDTO create(PublicationDTO publicationDTO) throws AlreadyExistsDataException, RequiredParametersDataException;
    PublicationDTO update(UUID uuid, PublicationDTO publicationDTO) throws NotFoundDataException, RequiredParametersDataException;
    PublicationDTO delete(UUID uuid) throws NotFoundDataException;
}
