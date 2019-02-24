package ua.ita.smartcarservice.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class RecordDto {

    private String sensorType;

    private String carVin;

    private String date;

    private Map<String, Double> values;

}