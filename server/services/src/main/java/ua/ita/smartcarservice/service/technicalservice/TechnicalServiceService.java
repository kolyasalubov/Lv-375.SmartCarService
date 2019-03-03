package ua.ita.smartcarservice.service.technicalservice;

import org.apache.catalina.User;
import ua.ita.smartcarservice.dto.sales.DealerStoAddDto;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import java.util.List;

public interface TechnicalServiceService {

    TechnicalServiceEntity getTechnicalServiceById(Long id);

    void createTechnicalService(String name, String address, Long userId);

    TechnicalServiceDto getTechnicalServiceDtoByUser(Long userId);

    TechnicalServiceDto getTechnicalServiceDtoByUser(String username);

    void deleteTechnicalService(Long id);

    void addUserToTechnicalService(String username, Long technicalServiceId);

    void deleteUserFromTechnicalService(String username, Long technicalServiceId);

    List<UserEntity> getUsersByRoleAndTechnicalSevice(String roleName, Long technicalServiceId);

    List<TechnicalServiceEntity> getAllTechnicalServices();

    List<TechnicalServiceDto> getAllTechnicalServicesDto();

    TechnicalServiceEntity getByTechnicalManager(UserEntity technicalManager);

    TechnicalServiceDto getTechnicalServiceDtoById(Long id);

    TechnicalServiceDto updateTechnicalService(TechnicalServiceEntity technicalService);

    TechnicalServiceDto updateTechnicalService(TechnicalServiceDto technicalServiceDto);

    List<TechnicalServiceDto> getAllTechnicalServicesDtoByDealer(String username);

    String findTechnicalServiceByCarId(Long id);
    void createTechnicalServiceByDealer(DealerStoAddDto stoAddDto, String username);

}
