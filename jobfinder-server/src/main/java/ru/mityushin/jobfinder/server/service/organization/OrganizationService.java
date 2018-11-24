package ru.mityushin.jobfinder.server.service.organization;

import ru.mityushin.jobfinder.server.util.dto.OrganizationDTO;
import ru.mityushin.jobfinder.server.util.dto.UserInfoDTO;
import ru.mityushin.jobfinder.server.util.exception.data.DataCreateException;
import ru.mityushin.jobfinder.server.util.exception.data.DataDeleteException;
import ru.mityushin.jobfinder.server.util.exception.data.DataUpdateException;

import java.util.List;

public interface OrganizationService {

    List<OrganizationDTO> findAll();
    List<OrganizationDTO> findAllDeleted();

    OrganizationDTO findByUrl(String url);

    OrganizationDTO create(OrganizationDTO organization) throws DataCreateException;
    OrganizationDTO update(OrganizationDTO organization) throws DataUpdateException;
    OrganizationDTO delete(OrganizationDTO organization) throws DataDeleteException;

    void addMember(OrganizationDTO organization, UserInfoDTO member);
    void deleteMember(OrganizationDTO organization, UserInfoDTO member);

    void addSubscriber(OrganizationDTO organization, UserInfoDTO subscriber);
    void deleteSubscriber(OrganizationDTO organization, UserInfoDTO subscriber);
}
