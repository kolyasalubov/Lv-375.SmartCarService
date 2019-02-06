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

    @PostMapping("/day")
    public ResponseEntity<ChartDto> addRecord(@RequestBody DateForChartDto dateForChartDto){
        ChartDto chartDto = sensorService.getAllByDay(dateForChartDto);
        if(chartDto.getData().size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(chartDto, HttpStatus.OK);
    }
}
