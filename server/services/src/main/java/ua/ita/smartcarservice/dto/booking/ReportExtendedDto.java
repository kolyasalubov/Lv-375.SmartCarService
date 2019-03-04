package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class ReportExtendedDto {

    // Car
    private String carBrand;
    private String carModel;
    private String carNumber;

    // User
    private String userFullName;

    // ReportEntity
    private String startTime;
    private String endTime;
    private int requiredTime;
    private int price;

    // TechnicalService
    private String technicalServiceName;
    private String technicalServiceAddress;

    private List<WorkerTasksDto> workerTasks;
}
