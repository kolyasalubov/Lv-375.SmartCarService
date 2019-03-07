package ua.ita.smartcarservice.repository.sensors.common;

import lombok.Data;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.sensors.enums.SensorProperties;
import ua.ita.smartcarservice.entity.sensors.enums.SensorTypes;
import ua.ita.smartcarservice.entity.sensors.enums.Tires;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Data
public class ChartCriteriaElementsProvider {

    private Path<String> datePath;
    private Map<String, Path<Double>> valuePath;
    private Path<Car> carIdPath;

    private long carId;
    private LocalDateTime date;
    private String selection;
    private String sensorType;

    public ChartCriteriaElementsProvider(long carId, LocalDateTime date, String selection, String sensorType, Root root) {
        this.carId = carId;
        this.date = date;
        this.selection = selection;
        this.sensorType = sensorType;
        setPathes(root);
    }

    public void setPathes(Root root) {
        datePath = root.get(SensorProperties.DATE.toString());
        carIdPath = root.get(SensorProperties.CAR.toString()).get(SensorProperties.ID.toString());
        valuePath = getValuePath(root, sensorType);
    }

    private Map<String, Path<Double>> getValuePath(Root root, String sensorType) {

        // TODO Map COLLECTORS
        Map<String, Path<Double>> valuePath = new HashMap<>();
        Arrays.stream(getValuesNames(sensorType)).forEach((value) -> {
            String key = value.toString();
            valuePath.put(key, root.get(key));
        });
        return valuePath;
    }

    private Object[] getValuesNames(String sensorType) {
        if (sensorType.equals(SensorTypes.TIRE_PRESSURE.toString())) {
            return Tires.values();
        } else {
            return new Object[]{SensorProperties.VALUE.toString()};
        }
    }

}
