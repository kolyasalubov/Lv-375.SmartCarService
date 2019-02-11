package ua.ita.smartcarservice.controller.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.IChartDto;
import ua.ita.smartcarservice.repository.sensors.factory.SensorFactory;
import ua.ita.smartcarservice.service.sensors.SensorService;

@RestController
@RequestMapping(value = "/api/chart")
public class ChartController {

    @Autowired
    private SensorFactory factory;

    private SensorService getService(DateForChartDto dateForChartDto){
        return factory.getService(dateForChartDto.getSensorType());
    }

    @PostMapping("/day")
    public ResponseEntity<IChartDto> getAllByDay(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getAllByDay(dateForChartDto));
    }

    @PostMapping("/month")
    public ResponseEntity<IChartDto> getAllByMonth(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getAvgByMonth(dateForChartDto));
    }

    @PostMapping("/month/min")
    public ResponseEntity<IChartDto> getMinByMonth(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getMinByMonth(dateForChartDto));
    }

    @PostMapping("/month/max")
    public ResponseEntity<IChartDto> getMaxByMonth(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getMaxByMonth(dateForChartDto));
    }

    @PostMapping("/year")
    public ResponseEntity<IChartDto> getAllByYear(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getAvgByYear(dateForChartDto));
    }

    @PostMapping("/year/min")
    public ResponseEntity<IChartDto> getMinByYear(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getMinByYear(dateForChartDto));
    }

    @PostMapping("/year/max")
    public ResponseEntity<IChartDto> getMaxByYear(@RequestBody DateForChartDto dateForChartDto){
        return getResponse(getService(dateForChartDto).getMaxByYear(dateForChartDto));
    }

    private ResponseEntity<IChartDto> getResponse(IChartDto chartDto){
        if(chartDto.dataSize() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        System.out.println(chartDto.toString());


        return new ResponseEntity<>(chartDto, HttpStatus.OK);
    }
}
