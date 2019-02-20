package ua.ita.smartcarservice.dto.sensors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChartDto {

    private Map<String, List<Double>> data; // keys --> labels

    public int dataSize() {
        return data.size();
    }
}
