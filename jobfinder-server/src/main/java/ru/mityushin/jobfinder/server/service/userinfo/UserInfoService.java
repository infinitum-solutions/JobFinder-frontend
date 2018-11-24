package ru.mityushin.jobfinder.server.service.userinfo;

import ru.mityushin.jobfinder.server.util.dto.UserInfoDTO;
import ru.mityushin.jobfinder.server.util.exception.data.DataCreateException;
import ru.mityushin.jobfinder.server.util.exception.data.DataDeleteException;
import ru.mityushin.jobfinder.server.util.exception.data.DataUpdateException;

import java.util.List;

public interface UserInfoService {

    List<UserInfoDTO> findAll();
    List<UserInfoDTO> findAllDeleted();

    UserInfoDTO findByUsername(String username);
    UserInfoDTO getCurrentAuthenticateUserInfo();

    UserInfoDTO findByUrl(String url);

    UserInfoDTO create(UserInfoDTO user) throws DataCreateException;
    UserInfoDTO update(UserInfoDTO user) throws DataUpdateException;
    UserInfoDTO delete(UserInfoDTO user) throws DataDeleteException;

    boolean existsByUsername(String username);
}
