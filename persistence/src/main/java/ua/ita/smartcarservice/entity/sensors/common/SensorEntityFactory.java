package ua.ita.smartcarservice.entity.sensors.common;

import lombok.Data;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.sensors.ISensorEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SensorEntityFactory {

    private Map<String, Class> entityFactory = new HashMap<>();

    public SensorEntityFactory() {
        entityInit();
    }

    private void entityInit() {
        Arrays.stream(SensorEntities.values())
                .forEach( entity -> entityFactory.put(entity.getSensorType(), entity.getSensorEntity()));
    }

    public ISensorEntity getEntity(String type) {
        try {
            return (ISensorEntity) entityFactory.get(type).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
