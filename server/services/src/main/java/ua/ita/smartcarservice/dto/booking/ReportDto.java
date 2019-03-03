package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

@Data
public class ReportDto {
    String startTime;
    String endTime;
    int cost;
    Long carId;
}
