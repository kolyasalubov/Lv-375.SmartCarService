package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.data.OilLevelEntity;
import ua.ita.smartcarservice.repository.sensors.factory.SensorRepository;

@Repository
public interface OilLevelRepository extends SensorRepository<OilLevelEntity> {
}
