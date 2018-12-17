package ru.mityushin.jobfinder.server.service.publication;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mityushin.jobfinder.server.model.Publication;
import ru.mityushin.jobfinder.server.repo.PublicationRepository;
import ru.mityushin.jobfinder.server.util.JobFinderUtils;
import ru.mityushin.jobfinder.server.util.dto.PublicationDTO;
import ru.mityushin.jobfinder.server.util.exception.data.DataNotFoundException;
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
                .filter((Publication p) -> !p.getDeleted())
                .map(PublicationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PublicationDTO find(UUID uuid) {
        Publication publication = publicationRepository.findByUuid(uuid);
        if (publication == null
                || publication.getDeleted()) {
            throw new DataNotFoundException("This publication has been deleted or has not been created yet.");
        }
        return PublicationMapper.map(publication);
    }

    @Override
    public PublicationDTO create(PublicationDTO publicationDTO) {
        Publication publication = PublicationMapper.map(publicationDTO);
        publication.setUuid(UUID.randomUUID());
        publication.setAuthorUuid(JobFinderUtils.getPrincipalIdentifier());
        publication.setVisible(Boolean.TRUE);
        publication.setDeleted(Boolean.FALSE);
        return PublicationMapper.map(publicationRepository.save(publication));
    }

    @Override
    public PublicationDTO update(UUID uuid, PublicationDTO publicationDTO) {

        Publication publicationFromRepo = publicationRepository.findByUuid(uuid);
        if (publicationFromRepo == null
                || publicationFromRepo.getDeleted()) {
            throw new DataNotFoundException("This publication has been deleted or has not been created yet.");
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
    public PublicationDTO delete(UUID uuid) throws DataNotFoundException {
        Publication publication = publicationRepository.findByUuid(uuid);
        if (publication == null
                || publication.getDeleted()) {
            throw new DataNotFoundException("This publication has been deleted or has not been created yet.");
        }
        publication.setDeleted(Boolean.TRUE);
        return PublicationMapper.map(publicationRepository.save(publication));
    }
}
