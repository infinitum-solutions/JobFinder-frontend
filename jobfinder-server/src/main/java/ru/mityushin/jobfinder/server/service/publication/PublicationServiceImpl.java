package ru.mityushin.jobfinder.server.service.publication;

import org.springframework.stereotype.Service;
import ru.mityushin.jobfinder.server.repo.PublicationRepository;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final static String LINK_PREFIX = "story_";

    private PublicationRepository publicationRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

}
