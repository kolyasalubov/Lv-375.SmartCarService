package com.smartcarservice.ua.SmartCarService.dto.sensors;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordDto {

    private String sensorType;

    private double value;

    private long carVin;

    private LocalDateTime date;
}
