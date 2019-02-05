package com.smartcarservice.ua.SmartCarService.entity.sensors.data;

import java.time.LocalDateTime;

public interface ISensorEntity {

    void setDate(LocalDateTime date);

    LocalDateTime getDate();

    void setValue(double value);

    double getValue();

}
