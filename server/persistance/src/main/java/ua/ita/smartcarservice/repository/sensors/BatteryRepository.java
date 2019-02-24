package ua.ita.smartcarservice.repository.sensors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.BatteryEntity;
import ua.ita.smartcarservice.repository.sensors.common.ChartSensorRepository;

@Repository
public interface BatteryRepository extends ChartSensorRepository<BatteryEntity> {

    @Query("SELECT t.value FROM #{#entityName} t WHERE t.id = " +
            "(SELECT MAX(e.id) FROM #{#entityName} e " +
            "WHERE e.car.id = :carId)")
    Double getLastValue(@Param("carId") long carId);
}
