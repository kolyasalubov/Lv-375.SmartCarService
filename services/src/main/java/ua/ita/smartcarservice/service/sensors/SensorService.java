package ua.ita.smartcarservice.service.sensors;

import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.service.impl.sensors.ParamsProvider;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface SensorService {

    /* READ */

    ChartDto getAllByDay(DateForChartDto dateForChartDto);

    ChartDto getAvgByMonth(DateForChartDto dateForChartDto);

    ChartDto getMaxByMonth(DateForChartDto dateForChartDto);

    ChartDto getMinByMonth(DateForChartDto dateForChartDto);

    ChartDto getAvgByYear(DateForChartDto dateForChartDto);

    ChartDto getMaxByYear(DateForChartDto dateForChartDto);

    ChartDto getMinByYear(DateForChartDto dateForChartDto);

    RecordDto findRecordBeforeDate (RecordDto recordDto);

    /* CREATE */

    void addRecord(RecordDto recordDto);

    /* DEFAULT METHODS */

    default LocalDateTime parseDateToLocal(String strDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(strDate, formatter);
    }

    default ParamsProvider getParams(DateForChartDto dateForChartDto){
        LocalDateTime date = parseDateToLocal(dateForChartDto.getDate());
        Long carId = dateForChartDto.getCarId();
        return new ParamsProvider(date, carId);
    }
}
