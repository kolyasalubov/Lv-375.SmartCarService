package ua.ita.smartcarservice.service.impl.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ParamsProvider {

    LocalDateTime date;
    Long carId;

    ParamsProvider(DateForChartDto dateForChartDto){
        date = DateParser.parseDateToLocal(dateForChartDto.getDate());
        carId = dateForChartDto.getCarId();
    }

}
