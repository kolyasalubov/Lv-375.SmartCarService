package ua.ita.smartcarservice.service.impl.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.feedback.ServicesFeedback;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.feedback.ServicesFeedbackRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.service.feedback.ServicesFeedbackService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@Service
public class ServicesFeedbackServiceImpl implements ServicesFeedbackService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TechnicalServiceRepository technicalServiceRepository;

    @Autowired
    ServicesFeedbackRepository servicesFeedbackRepository;

    @Override
    public void addFeedbackToService(String text, String username, Long serviceId) {
        ServicesFeedback servicesFeedback;
        UserEntity userEntity;
        TechnicalServiceEntity technicalServiceEntity;

        userEntity = userRepository.getByUsername(username);
        technicalServiceEntity = technicalServiceRepository.getOne(serviceId);
        servicesFeedback  = new ServicesFeedback(text, LocalDateTime.now(), userEntity, technicalServiceEntity);
    }

    @Override
    public List<ServicesFeedbackDto> getServicesFeedback(Long serviceId) {
        List<ServicesFeedbackDto> servicesFeedbackDtoList = new ArrayList<>();
        List<ServicesFeedback> servicesFeedbackEntityList;
        TechnicalServiceEntity technicalServiceEntity;

        technicalServiceEntity = technicalServiceRepository.getOne(serviceId);
        servicesFeedbackEntityList = servicesFeedbackRepository.getByServiceId(technicalServiceEntity);

        servicesFeedbackEntityList.parallelStream().forEach(eachEntity -> {
            servicesFeedbackDtoList.add(this.getServicesFeedbackDtoFromEntity(eachEntity));
        });

        return servicesFeedbackDtoList;
    }

    @Override
    public List<ServicesFeedbackDto> getUsersFeedback(String username) {
        List<ServicesFeedbackDto> servicesFeedbackDtoList = new ArrayList<>();
        List<ServicesFeedback> servicesFeedbackEntityList;
        UserEntity userEntity;

        userEntity = userRepository.getByUsername(username);
        servicesFeedbackEntityList = servicesFeedbackRepository.getByUserId(userEntity);

        servicesFeedbackEntityList.parallelStream().forEach(eachEntity -> {
            servicesFeedbackDtoList.add(this.getServicesFeedbackDtoFromEntity(eachEntity));
        });

        return servicesFeedbackDtoList;
    }

    @Override
    public Double getServicesRating(Long serviceId) {
        Double rating;
        TechnicalServiceEntity technicalServiceEntity;

        technicalServiceEntity = technicalServiceRepository.getOne(serviceId);
        rating = servicesFeedbackRepository.getServicesRating(technicalServiceEntity);
        rating = BigDecimal.valueOf(rating).setScale(1, RoundingMode.HALF_UP).doubleValue();

        return rating;
    }

    private ServicesFeedbackDto getServicesFeedbackDtoFromEntity(ServicesFeedback servicesFeedbackEntity){
        ServicesFeedbackDto servicesFeedbackDto;

        servicesFeedbackDto = new ServicesFeedbackDto(
                servicesFeedbackEntity.getId(),
                servicesFeedbackEntity.getText(),
                servicesFeedbackEntity.getTime().format(ISO_LOCAL_DATE),
                servicesFeedbackEntity.getUserId().getFullName(),
                servicesFeedbackEntity.getServiceId().getName()
        );

        return servicesFeedbackDto;
    }

}
