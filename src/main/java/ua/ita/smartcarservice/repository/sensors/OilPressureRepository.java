package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.data.OilPressureEntity;
import ua.ita.smartcarservice.repository.sensors.factory.SensorRepository;

@Repository
public interface OilPressureRepository extends SensorRepository<OilPressureEntity> {
}
