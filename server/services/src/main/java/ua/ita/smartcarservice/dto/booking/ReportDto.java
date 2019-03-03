package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

@Data
public class ReportDto {

    private long carId;

    private long technicalServiceId;

    private String startTime;

    private String endTime;

    private int price;

}
