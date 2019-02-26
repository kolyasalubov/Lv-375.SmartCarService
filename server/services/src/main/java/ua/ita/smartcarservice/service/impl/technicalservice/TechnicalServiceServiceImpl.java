package ua.ita.smartcarservice.service.impl.technicalservice;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
public class TechnicalServiceServiceImpl implements TechnicalServiceService {

    @Autowired
    TechnicalServiceRepository technicalServiceRepository;

    @Autowired
    UserTechnicalServiceRepository userTechnicalServiceRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addWorkerToTechnicalService(String username, Long technicalServiceId) {
        UserEntity workerEntity = userRepository.getByUsername(username);
        TechnicalServiceEntity technicalServiceEntity = technicalServiceRepository.getOne(technicalServiceId);

        userTechnicalServiceRepository.save(new UserTechnicalService(workerEntity, technicalServiceEntity));
    }

    @Override
    public void deleteWorkerFromTEchnicalService(String username, Long technicalServiceId) {

    }

    @Override
    public void createTechnicalService(String name, String address, Long userId) {
        TechnicalServiceEntity technicalServiceEntity = new TechnicalServiceEntity();
        UserEntity userEntity = userRepository.getOne(userId);

        technicalServiceEntity.setName(name);
        technicalServiceEntity.setAddress(address);

        technicalServiceEntity = technicalServiceRepository.save(technicalServiceEntity);

        userTechnicalServiceRepository.save(new UserTechnicalService(userEntity, technicalServiceEntity));
    }

    @Override
    public TechnicalServiceDto updateTechnicalService(TechnicalServiceEntity technicalService) {
        technicalServiceRepository.save(technicalService);
        return convertToDto(technicalServiceRepository.getOne(technicalService.getTechnicalServiceId()));
    }

    @Override
    public TechnicalServiceDto updateTechnicalService(TechnicalServiceDto technicalServiceDto) {
        TechnicalServiceEntity technicalService = convertToEntity(technicalServiceDto);

        return updateTechnicalService(technicalService);
    }

    @Override
    public void deleteTechnicalService(Long id) {
        technicalServiceRepository.deleteById(id);
        // userTechnicalServiceRepository.deleteByTechnicalServiceId(id);
    }

    @Override
    public TechnicalServiceDto getTechnicalServiceDtoById(Long id) {
        return convertToDto(getTechnicalServiceById(id));
    }

    @Override
    public List<UserEntity> getUsersByRoleAndTechnicalSevice(String roleName, Long technicalServiceId) {
        return userRepository.getUserEntitiesByRoleNameAndTechnicalService(roleName, technicalServiceId);
    }

    public TechnicalServiceDto convertToDto(TechnicalServiceEntity technicalServiceEntity) {
        TechnicalServiceDto dto = new TechnicalServiceDto();

        dto.setStoId(technicalServiceEntity.getTechnicalServiceId());
        dto.setName(technicalServiceEntity.getName());
        dto.setAddress(technicalServiceEntity.getAddress());
        //dto.setTechnicalManager(userTechnicalServiceRepository.getTechnicalServiceManagerByServiceId(technicalServiceEntity));

        return dto;
    }

    @Override
    public TechnicalServiceDto getTechnicalServiceDtoByUser(Long userId) {
        try {
            TechnicalServiceEntity entity = technicalServiceRepository.getTechnicalServiceEntityByUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertToDto(technicalServiceRepository.getTechnicalServiceEntityByUser(userId));
    }

    public TechnicalServiceEntity convertToEntity(TechnicalServiceDto technicalServiceDto) {
        TechnicalServiceEntity entity = new TechnicalServiceEntity();

        entity.setTechnicalServiceId(technicalServiceDto.getStoId());
        entity.setName(technicalServiceDto.getName());
        entity.setAddress(technicalServiceDto.getAddress());

        //entity.setTechnicalManager(userTechnicalServiceRepository.getOne(technicalServiceDto.getStoId()).getUserId());
        //entity.setWorkers(technicalServiceDto.getWorkerSet());

        return entity;
    }

    @Override
    public List<TechnicalServiceEntity> getAllTechnicalServices() {
        return technicalServiceRepository.findAll();
    }

    @Override
    public List<TechnicalServiceDto> getAllTechnicalServicesDto() {
        List<TechnicalServiceEntity> technicalServiceList = technicalServiceRepository.findAll();
        List<TechnicalServiceDto> technicalServiceDtoList = new ArrayList<>();

        for (TechnicalServiceEntity eachTechnicalService : technicalServiceList) {
            technicalServiceDtoList.add(convertToDto(eachTechnicalService));
        }
        return technicalServiceDtoList;
    }

    @Override
    public TechnicalServiceEntity getByTechnicalManager(UserEntity technicalManager) {
        return null;// technicalServiceRepository.getByTechnicalManager(technicalManager);
    }

    @Override
    public TechnicalServiceEntity getTechnicalServiceById(Long id) {
        return technicalServiceRepository.getOne(id);
    }

    @Override
    public String findTechnicalServiceByCarId(Long id){
        return userTechnicalServiceRepository.findTechnicalServiceByCarId(id);
    }
}
