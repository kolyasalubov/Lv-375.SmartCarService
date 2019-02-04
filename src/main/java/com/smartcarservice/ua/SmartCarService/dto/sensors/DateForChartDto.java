package com.smartcarservice.ua.SmartCarService.dto.sensors;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateForChartDto {

    private String sensorType;

    private long carId;

    private LocalDateTime date;

}
