package ua.ita.smartcarservice.repository.sensors.common;

import org.springframework.data.repository.NoRepositoryBean;
import ua.ita.smartcarservice.entity.sensors.BaseSensorEntity;

@NoRepositoryBean
public interface AlertSensorRepository<T extends BaseSensorEntity> extends BasicSensorRepository<T> {


}

