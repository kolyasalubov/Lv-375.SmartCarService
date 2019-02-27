package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;

public interface SensorService {

    /* READ */

    ChartDto findLastValue(DateForChartDto dateForChartDto);

    ChartDto findAllByDay(DateForChartDto dateForChartDto);

    ChartDto findAvgByMonth(DateForChartDto dateForChartDto);

    ChartDto findMaxByMonth(DateForChartDto dateForChartDto);

    ChartDto findMinByMonth(DateForChartDto dateForChartDto);

    ChartDto findAvgByYear(DateForChartDto dateForChartDto);

    ChartDto findMaxByYear(DateForChartDto dateForChartDto);

    ChartDto findMinByYear(DateForChartDto dateForChartDto);

    RecordDto findRecordBeforeDate (RecordDto recordDto);

    /* CREATE */

    void addRecord(RecordDto recordDto);


}
