package ua.ita.smartcarservice.repository.sensors.common;

import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.entity.sensors.BaseSensorEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ChartSensorRepository<T extends BaseSensorEntity> {

    List findAllDataByPeriod(@Param("date") LocalDateTime date,
                             @Param("carId") long carId,
                             @Param("sensorType") String sensorType,
                             @Param("selection") String selection);

    Double findLastRecordValue(@Param("carId") long carId,
                               @Param("sensorType") String sensorType);

}

