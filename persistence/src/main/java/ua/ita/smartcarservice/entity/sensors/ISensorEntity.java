package ua.ita.smartcarservice.entity.sensors;


import ua.ita.smartcarservice.entity.Car;

import java.time.LocalDateTime;
import java.util.Map;

public interface ISensorEntity {

    void setDate(LocalDateTime date);

    LocalDateTime getDate();

    Car getCar();

    void setCar(Car car);

    ISensorEntity setValues(ISensorEntity entity, Map<String, Double> values);

}
