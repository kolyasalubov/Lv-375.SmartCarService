package ua.ita.smartcarservice.service.impl.technicalservice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sales.DealerStoAddDto;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.Roles;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.sales.DealerEntityRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.service.feedback.ServicesFeedbackService;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of TechnicalService Service.
 */
@Service
public final class TechnicalServiceServiceImpl
        implements TechnicalServiceService {

    @Autowired
    private TechnicalServiceRepository
            technicalServiceRepository;

    @Autowired
    private ServicesFeedbackService
            servicesFeedbackService;

    @Autowired
    private UserTechnicalServiceRepository
            userTechnicalServiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DealerEntityRepository
            dealerEntityRepository;

    private static final Logger LOGGER =
        Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());


    @Override
    public TechnicalServiceDto getTechnicalServiceDtoByUser(
            final String username) {
        TechnicalServiceEntity technicalServiceEntity;
        TechnicalServiceDto technicalServiceDto;

        technicalServiceEntity = technicalServiceRepository.
                getTechnicalServiceEntityByUsername(username);
        technicalServiceDto = this.convertToDto(technicalServiceEntity);

        return technicalServiceDto;
    }

    @Override
    public void addUserToTechnicalService(final String username,
                                          final Long technicalServiceId) {
        UserEntity workerEntity = userRepository.getByUsername(username);
        TechnicalServiceEntity technicalServiceEntity =
                technicalServiceRepository.getOne(technicalServiceId);

        userTechnicalServiceRepository.save(
                new UserTechnicalService(workerEntity, technicalServiceEntity));
    }

    @Override
    public void deleteUserFromTechnicalService(final String username,
                                               final Long technicalServiceId) {
        //userTechnicalServiceRepository.
    }

    @Override
    public void createTechnicalService(final String name,
                                       final String address,
                                       final Long userId) {
        TechnicalServiceEntity technicalServiceEntity =
                new TechnicalServiceEntity();
        UserEntity userEntity = userRepository.getOne(userId);

        technicalServiceEntity.setName(name);
        technicalServiceEntity.setAddress(address);

        technicalServiceEntity =
                technicalServiceRepository.save(technicalServiceEntity);

        userTechnicalServiceRepository.save(
                new UserTechnicalService(userEntity, technicalServiceEntity));
    }

    @Override
    public TechnicalServiceDto updateTechnicalService(
            final TechnicalServiceEntity technicalService) {
        technicalServiceRepository.save(technicalService);
        return convertToDto(technicalServiceRepository.
                getOne(technicalService.getTechnicalServiceId()));
    }

    @Override
    public TechnicalServiceDto updateTechnicalService(
            final TechnicalServiceDto technicalServiceDto) {
        TechnicalServiceEntity technicalService =
                convertToEntity(technicalServiceDto);

        return updateTechnicalService(technicalService);
    }

    @Override
    public void deleteTechnicalService(final Long id) {
        technicalServiceRepository.deleteById(id);
    }

    @Override
    public TechnicalServiceDto getTechnicalServiceDtoById(final Long id) {
        return convertToDto(getTechnicalServiceById(id));
    }

    @Override
    public List<UserEntity> getUsersByRoleAndTechnicalSevice(
                                        final String roleName,
                                        final Long technicalServiceId) {
        return userRepository.getUserEntitiesByRoleNameAndTechnicalService(
                roleName, technicalServiceId);
    }

    public TechnicalServiceDto convertToDto(
            final TechnicalServiceEntity technicalServiceEntity) {
        TechnicalServiceDto dto = new TechnicalServiceDto();

        dto.setStoId(technicalServiceEntity.getTechnicalServiceId());
        dto.setName(technicalServiceEntity.getName());
        dto.setAddress(technicalServiceEntity.getAddress());
        dto.setRating(servicesFeedbackService.getServicesRating(
                technicalServiceEntity.getTechnicalServiceId()));
        dto.setWorkers(getUsersByRoleAndTechnicalSevice(
                Roles.ROLE_WORKER.toString(),
                technicalServiceEntity.getTechnicalServiceId()));
        return dto;
    }

    @Override
    public TechnicalServiceDto getTechnicalServiceDtoByUser(final Long userId) {
        return convertToDto(technicalServiceRepository.
                getTechnicalServiceEntityByUser(userId));
    }

    public TechnicalServiceEntity convertToEntity(
            final TechnicalServiceDto technicalServiceDto) {
        TechnicalServiceEntity entity = new TechnicalServiceEntity();

        entity.setTechnicalServiceId(technicalServiceDto.getStoId());
        entity.setName(technicalServiceDto.getName());
        entity.setAddress(technicalServiceDto.getAddress());

        return entity;
    }

    @Override
    public List<TechnicalServiceEntity> getAllTechnicalServices() {
        return technicalServiceRepository.findAll();
    }

    @Override
    public List<TechnicalServiceDto> getAllTechnicalServicesDto() {
        List<TechnicalServiceEntity> technicalServiceList =
                technicalServiceRepository.findAll();
        List<TechnicalServiceDto> technicalServiceDtoList = new ArrayList<>();

        for (TechnicalServiceEntity eachTechnicalService : technicalServiceList) {
            technicalServiceDtoList.add(convertToDto(eachTechnicalService));
        }
        return technicalServiceDtoList;
    }

    @Override
    public TechnicalServiceEntity getTechnicalServiceById(final Long id) {
        return technicalServiceRepository.getOne(id);
    }

    @Override
    public String findTechnicalServiceByCarId(final Long id) {
        return userTechnicalServiceRepository.
                findTechnicalServiceByCarId(id).
                    getTechnicalServiceId().getName();
    }

    @Override
    public List<TechnicalServiceDto> getAllTechnicalServicesDtoByDealer(
            final String username) {
        List<TechnicalServiceEntity> technicalServiceList =
                technicalServiceRepository.
                        findAllByDealer_UserEntity_Username(username);
        List<TechnicalServiceDto> technicalServiceDtoList = new ArrayList<>();

        technicalServiceList.parallelStream().forEach(technicalService -> {
            technicalServiceDtoList.add(convertToDto(technicalService));
        });

        return technicalServiceDtoList;
    }

    @Override
    public void createTechnicalServiceByDealer(
            final DealerStoAddDto stoAddDto,
            final String username) {
        technicalServiceRepository.save(new TechnicalServiceEntity(
                stoAddDto.getNameSto(),
                stoAddDto.getAddressSto(),
                dealerEntityRepository.findByUserEntity_Username(username)));
    }

}
