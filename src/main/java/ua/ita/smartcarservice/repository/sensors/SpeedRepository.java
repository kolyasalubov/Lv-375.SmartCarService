package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.data.SpeedEntity;
import ua.ita.smartcarservice.repository.sensors.factory.SensorRepository;

@Repository
public interface SpeedRepository extends SensorRepository<SpeedEntity> {
}
