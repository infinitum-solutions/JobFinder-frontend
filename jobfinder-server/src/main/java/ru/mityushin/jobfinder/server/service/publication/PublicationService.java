package ru.mityushin.jobfinder.server.service.publication;

import ru.mityushin.jobfinder.server.util.dto.PublicationDTO;
import ru.mityushin.jobfinder.server.util.dto.UserInfoDTO;
import ru.mityushin.jobfinder.server.util.exception.data.DataCreateException;
import ru.mityushin.jobfinder.server.util.exception.data.DataDeleteException;
import ru.mityushin.jobfinder.server.util.exception.data.DataUpdateException;

import java.util.List;

public interface PublicationService {

    List<PublicationDTO> findAll();
    List<PublicationDTO> findAllDeleted();
    List<PublicationDTO> findAllByAuthor(UserInfoDTO author);

    PublicationDTO findByUrl(String url);

    PublicationDTO create(PublicationDTO publication) throws DataCreateException;
    PublicationDTO update(PublicationDTO publication) throws DataUpdateException;
    PublicationDTO delete(PublicationDTO publication) throws DataDeleteException;
}
