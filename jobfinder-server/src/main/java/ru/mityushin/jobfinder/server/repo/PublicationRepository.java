package ru.mityushin.jobfinder.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mityushin.jobfinder.server.model.Publication;
import ru.mityushin.jobfinder.server.model.UserInfo;

import java.util.List;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, Long> {
    List<Publication> findAll();
    List<Publication> findAllByDeletedFalse();
    List<Publication> findAllByDeletedFalseAndVisibleTrue();

    List<Publication> findAllByAuthorAndDeletedFalse(UserInfo author);
    List<Publication> findAllByAuthorAndDeletedFalseAndVisibleTrue(UserInfo author);

    Publication findByUrl(String url);
    boolean existsByAuthor(UserInfo author);
}
