package ru.mityushin.jobfinder.server.service.publication;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mityushin.jobfinder.server.model.Publication;
import ru.mityushin.jobfinder.server.repo.PublicationRepository;
import ru.mityushin.jobfinder.server.util.JobFinderUtils;
import ru.mityushin.jobfinder.server.util.dto.PublicationDTO;
import ru.mityushin.jobfinder.server.util.exception.data.AlreadyExistsDataException;
import ru.mityushin.jobfinder.server.util.exception.data.NotFoundDataException;
import ru.mityushin.jobfinder.server.util.exception.data.RequiredParametersDataException;
import ru.mityushin.jobfinder.server.util.mapper.PublicationMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private PublicationRepository publicationRepository;

    @Override
    public List<PublicationDTO> findAll() {
        return publicationRepository.findAll().stream()
                .filter(o -> !o.getDeleted())
                .map(PublicationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PublicationDTO find(UUID uuid) {
        Publication publication = publicationRepository.findByUuid(uuid);
        if (publication == null
                || publication.getDeleted()) {
            throw new NotFoundDataException();
        }
        return PublicationMapper.map(publication);
    }

    @Override
    public PublicationDTO create(PublicationDTO publicationDTO) throws AlreadyExistsDataException, RequiredParametersDataException {
        Publication publication = PublicationMapper.map(publicationDTO);
        publication.setUuid(UUID.randomUUID());
        publication.setAuthorUuid(JobFinderUtils.getPrincipalIdentifier());
        Publication saved = publicationRepository.save(publication);
        return PublicationMapper.map(saved);
    }

    @Override
    public PublicationDTO update(UUID uuid, PublicationDTO publicationDTO) throws NotFoundDataException, RequiredParametersDataException {

        Publication publicationFromRepo = publicationRepository.findByUuid(uuid);
        if (publicationFromRepo == null
                || publicationFromRepo.getDeleted()) {
            throw new NotFoundDataException();
        }
        Publication publication = PublicationMapper.map(publicationDTO);
        publication.setId(publicationFromRepo.getId());
        publication.setUuid(uuid);
        publication.setAuthorUuid(publicationFromRepo.getAuthorUuid());
        publication.setDeleted(Boolean.FALSE);

        Publication saved = publicationRepository.save(publication);
        return PublicationMapper.map(saved);
    }

    @Override
    public PublicationDTO delete(UUID uuid) throws NotFoundDataException {
        Publication publication = publicationRepository.findByUuid(uuid);
        if (publication == null
                || publication.getDeleted()) {
            throw new NotFoundDataException();
        }
        publication.setDeleted(Boolean.TRUE);
        return PublicationMapper.map(publicationRepository.save(publication));
    }
}
