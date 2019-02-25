package ua.ita.smartcarservice.dto.feedback;

public class ServicesFeedbackDto {
    Long id;

    String text;

    String time;

    String fullName;

    String serviceName;

    public ServicesFeedbackDto(Long id, String text, String time, String fullName, String serviceName) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.fullName = fullName;
        this.serviceName = serviceName;
    }
}
