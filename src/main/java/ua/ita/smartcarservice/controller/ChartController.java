package ua.ita.smartcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.service.SensorService;

@RestController
@RequestMapping(value = "/chart")
public class ChartController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/day")
    public ResponseEntity<ChartDto> getAllByDay(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(sensorService.getAllByDay(dateForChartDto));
    }

    @GetMapping("/month")
    public ResponseEntity<ChartDto> getAllByMonth(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(sensorService.getAvgByMonth(dateForChartDto));
    }

    @GetMapping("/month/min")
    public ResponseEntity<ChartDto> getMinByMonth(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(sensorService.getMinByMonth(dateForChartDto));
    }

    @GetMapping("/month/max")
    public ResponseEntity<ChartDto> getMaxByMonth(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(sensorService.getMaxByMonth(dateForChartDto));
    }

    @GetMapping("/year")
    public ResponseEntity<ChartDto> getAllByYear(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(sensorService.getAvgByYear(dateForChartDto));
    }

    @GetMapping("/year/min")
    public ResponseEntity<ChartDto> getMinByYear(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(sensorService.getMinByYear(dateForChartDto));
    }

    @GetMapping("/year/max")
    public ResponseEntity<ChartDto> getMaxByYear(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(sensorService.getMaxByYear(dateForChartDto));
    }

    private ResponseEntity<ChartDto> getResponse(ChartDto chartDto){
        if(chartDto.getData().size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(chartDto, HttpStatus.OK);
    }
}
