package ua.ita.smartcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.IChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.repository.sensors.factory.SensorFactory;
import ua.ita.smartcarservice.service.SensorService;

@RestController
@RequestMapping(value = "/api/chart")
public class ChartController {

    @Autowired
    private SensorFactory factory;

    private SensorService getService(DateForChartDto dateForChartDto){
        return factory.getService(dateForChartDto.getSensorType());
    }

    @GetMapping("/day")
    public ResponseEntity<IChartDto> getAllByDay(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getAllByDay(dateForChartDto));
    }

    @GetMapping("/month")
    public ResponseEntity<IChartDto> getAllByMonth(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getAvgByMonth(dateForChartDto));
    }

    @GetMapping("/month/min")
    public ResponseEntity<IChartDto> getMinByMonth(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getMinByMonth(dateForChartDto));
    }

    @GetMapping("/month/max")
    public ResponseEntity<IChartDto> getMaxByMonth(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getMaxByMonth(dateForChartDto));
    }

    @GetMapping("/year")
    public ResponseEntity<IChartDto> getAllByYear(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getAvgByYear(dateForChartDto));
    }

    @GetMapping("/year/min")
    public ResponseEntity<IChartDto> getMinByYear(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getMinByYear(dateForChartDto));
    }

    @GetMapping("/year/max")
    public ResponseEntity<IChartDto> getMaxByYear(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getMaxByYear(dateForChartDto));
    }

    private ResponseEntity<IChartDto> getResponse(IChartDto chartDto){
        if(chartDto.dataSize() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(chartDto, HttpStatus.OK);
    }
}
