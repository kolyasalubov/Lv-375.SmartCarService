package com.smartcarservice.ua.SmartCarService.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RecordDto {

    private String sensorType;

    private double value;

    private long carVin;

    private LocalDateTime date;

    public RecordDto(String sensorType, double value, long carVin, LocalDateTime date) {
        this.sensorType = sensorType;
        this.value = value;
        this.carVin = carVin;
        this.date = date;
    }
}
