package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.sensors.*;

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

}
