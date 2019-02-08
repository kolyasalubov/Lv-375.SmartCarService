package ua.ita.smartcarservice.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChartDto implements IChartDto {

    private List<Double> data;

    private List<String> labels;

    public ChartDto(List<Double> data, List<String> labels) {
        this.data = data;
        this.labels = labels;
    }

    public int dataSize(){
        return data.size();
    }
}
