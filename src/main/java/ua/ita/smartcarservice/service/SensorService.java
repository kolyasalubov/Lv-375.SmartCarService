package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;

public interface SensorService {

    /* READ */

    ChartDto getAllByDay(DateForChartDto dateForChartDto);

    ChartDto getAvgByMonth(DateForChartDto dateForChartDto);

    ChartDto getMaxByMonth(DateForChartDto dateForChartDto);

    ChartDto getMinByMonth(DateForChartDto dateForChartDto);

    ChartDto getAvgByYear(DateForChartDto dateForChartDto);

    ChartDto getMaxByYear(DateForChartDto dateForChartDto);

    ChartDto getMinByYear(DateForChartDto dateForChartDto);


    /* CREATE */

    void addRecord(RecordDto recordDto);

}
