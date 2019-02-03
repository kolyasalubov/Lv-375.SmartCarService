package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.dto.sensors.ChartDto;
import com.smartcarservice.ua.SmartCarService.dto.sensors.DateForChartDto;
import com.smartcarservice.ua.SmartCarService.dto.sensors.RecordDto;

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
