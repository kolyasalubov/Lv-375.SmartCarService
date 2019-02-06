package ua.ita.smartcarservice.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordDto implements IRecordDto {

    private String sensorType;

    private double value;

    private String carVin;

    private String date;

    public RecordDto(String sensorType, double value, String carVin, String date) {
        this.sensorType = sensorType;
        this.value = value;
        this.carVin = carVin;
        this.date = date;
    }
}
