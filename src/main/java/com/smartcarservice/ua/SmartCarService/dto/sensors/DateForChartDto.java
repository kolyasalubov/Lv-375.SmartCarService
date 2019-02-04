package com.smartcarservice.ua.SmartCarService.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DateForChartDto {

    private String sensorType;

    private long carId;

    private LocalDateTime date;

    public DateForChartDto(String sensorType, long carId, LocalDateTime date) {
        this.sensorType = sensorType;
        this.carId = carId;
        this.date = date;
    }
}
