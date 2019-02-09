package ua.ita.smartcarservice.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DateForChartDto {

    private String sensorType;

    private long carId;

    private String date;
}
