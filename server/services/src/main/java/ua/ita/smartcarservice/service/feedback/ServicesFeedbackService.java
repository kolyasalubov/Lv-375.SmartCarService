package ua.ita.smartcarservice.service.feedback;

import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackDto;
import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackInputDto;
import ua.ita.smartcarservice.entity.feedback.ServicesFeedback;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicesFeedbackService {
    void addFeedbackToService(ServicesFeedbackInputDto servicesFeedbackInputDto);

    List<ServicesFeedbackDto> getServicesFeedback(Long serviceId);

    List<ServicesFeedbackDto> getUsersFeedback(String username);

    Double getServicesRating(Long serviceId);
}

