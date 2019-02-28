package ua.ita.smartcarservice.dto.feedback;

import java.io.Serializable;

public class ServicesFeedbackOutputDto {
    Long id;

    String text;

    String time;

    String fullName;

    String serviceName;

    public ServicesFeedbackOutputDto(Long id, String text, String time, String fullName, String serviceName) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.fullName = fullName;
        this.serviceName = serviceName;
    }
}
