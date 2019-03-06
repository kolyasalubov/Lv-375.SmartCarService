package ua.ita.smartcarservice.dto.sensors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChartDto {

    private Map<String, List<Double>> data = new HashMap<>(); // keys --> labels

    // TODO delete these constructors or ADD COMMENTS

    public ChartDto(Double value){
        this.data.put("value", Arrays.asList(value));
    }

    public ChartDto(Integer value){
        this.data.put("value", Arrays.asList((double)value));
    }
}
