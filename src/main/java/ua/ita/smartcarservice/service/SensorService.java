package ua.ita.smartcarservice.service;

import javafx.util.Pair;
import ua.ita.smartcarservice.dto.sensors.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface SensorService {

    /* READ */

    IChartDto getAllByDay(DateForChartDto dateForChartDto);

    IChartDto getAvgByMonth(DateForChartDto dateForChartDto);

    IChartDto getMaxByMonth(DateForChartDto dateForChartDto);

    IChartDto getMinByMonth(DateForChartDto dateForChartDto);

    IChartDto getAvgByYear(DateForChartDto dateForChartDto);

    IChartDto getMaxByYear(DateForChartDto dateForChartDto);

    IChartDto getMinByYear(DateForChartDto dateForChartDto);


    /* CREATE */

    void addRecord(ARecordDto recordDto);

    /* DEFAULT METHODS */

    default LocalDateTime parseDateToLocal (String strDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(strDate, formatter);
    }

    default Pair<LocalDateTime, Long> getParams(DateForChartDto dateForChartDto){
        LocalDateTime date = parseDateToLocal(dateForChartDto.getDate());
        Long carId = dateForChartDto.getCarId();
        return new Pair<>(date, carId);
    }
}
