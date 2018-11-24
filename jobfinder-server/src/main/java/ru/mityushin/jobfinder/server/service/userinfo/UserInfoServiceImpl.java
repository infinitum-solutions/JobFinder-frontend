package ru.mityushin.jobfinder.server.service.userinfo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mityushin.jobfinder.server.model.UserInfo;
import ru.mityushin.jobfinder.server.repo.RoleRepository;
import ru.mityushin.jobfinder.server.repo.UserInfoRepository;
import ru.mityushin.jobfinder.server.service.security.SecurityService;
import ru.mityushin.jobfinder.server.util.dto.UserInfoDTO;
import ru.mityushin.jobfinder.server.util.exception.*;
import ru.mityushin.jobfinder.server.util.exception.data.DataCreateException;
import ru.mityushin.jobfinder.server.util.exception.data.DataDeleteException;
import ru.mityushin.jobfinder.server.util.exception.data.DataUpdateException;
import ru.mityushin.jobfinder.server.util.exception.data.UserAlreadyExistsException;
import ru.mityushin.jobfinder.server.util.mapper.UserInfoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoRepository userInfoRepository;
    private SecurityService securityService;

    public UserInfoServiceImpl(
            UserInfoRepository userInfoRepository,
            RoleRepository roleRepository,
            SecurityService securityService,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.userInfoRepository = userInfoRepository;
        this.securityService = securityService;
    }

    @Override
    public List<UserInfoDTO> findAll() {
        return userInfoRepository.findAll().stream()
                .map(UserInfoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserInfoDTO> findAllDeleted() {
        return userInfoRepository.findAllByDeletedTrue().stream()
                .map(UserInfoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public UserInfoDTO findByUsername(String username) {
        UserInfo user = userInfoRepository.findByUsername(username);
        if (user == null) {
            throw new ServerException();
        }
        return UserInfoMapper.map(user);
    }

    @Override
    public UserInfoDTO getCurrentAuthenticateUserInfo() {

        String loggedInUsername = securityService.findLoggedInUsername();

        return findByUsername(loggedInUsername);
    }

    @Override
    public UserInfoDTO findByUrl(String url) {
        if (url == null) {
            throw new ServerException();
        }
        UserInfo user = userInfoRepository.findByUrl(url);
        if (user == null) {
            throw new ServerException();
        }
        return UserInfoMapper.map(user);
    }

    @Override
    public UserInfoDTO create(UserInfoDTO user) throws DataCreateException {

        if (user == null) {
            throw new DataCreateException();
        }

        if (existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        UserInfo userInfo = UserInfoMapper.map(user);

        return UserInfoMapper.map(userInfoRepository.save(userInfo));
    }

    @Override
    public UserInfoDTO update(UserInfoDTO user) throws DataUpdateException {
        return null;
    }

    @Override
    public UserInfoDTO delete(UserInfoDTO user) throws DataDeleteException {
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userInfoRepository.existsByUsername(username);
    }
}
