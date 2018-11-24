package ru.mityushin.jobfinder.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mityushin.jobfinder.server.model.UserInfo;

import java.util.List;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
    List<UserInfo> findAll();
    List<UserInfo> findAllByDeletedTrue();
    UserInfo findByUrl(String url);
    UserInfo findByUsername(String username);
    Integer deleteByUsername(String username);
    boolean existsByUsername(String username);
}
