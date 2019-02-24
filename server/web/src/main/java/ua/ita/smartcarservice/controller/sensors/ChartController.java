package ua.ita.smartcarservice.controller.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.service.SensorService;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/chart")
public class ChartController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/last")
    public ResponseEntity<ChartDto> findLastValue(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findLastValue(new DateForChartDto(parametersMap)));
    }

    @GetMapping("/day")
    public ResponseEntity<ChartDto> findAllByDay(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findAllByDay(new DateForChartDto(parametersMap)));
    }

    @GetMapping("/month")
    public ResponseEntity<ChartDto> findAllByMonth(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findAvgByMonth(new DateForChartDto(parametersMap)));
    }

    @GetMapping("/month/min")
    public ResponseEntity<ChartDto> findMinByMonth(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findMinByMonth(new DateForChartDto(parametersMap)));
    }

    @GetMapping("/month/max")
    public ResponseEntity<ChartDto> findMaxByMonth(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findMaxByMonth(new DateForChartDto(parametersMap)));
    }

    @GetMapping("/year")
    public ResponseEntity<ChartDto> findAllByYear(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findAvgByYear(new DateForChartDto(parametersMap)));
    }

    @GetMapping("/year/min")
    public ResponseEntity<ChartDto> findMinByYear(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findMinByYear(new DateForChartDto(parametersMap)));
    }

    @GetMapping("/year/max")
    public ResponseEntity<ChartDto> findMaxByYear(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findMaxByYear(new DateForChartDto(parametersMap)));
    }

    private ResponseEntity<ChartDto> getResponse(ChartDto chartDto) {
        return (chartDto.getData().size() == 0) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(chartDto, HttpStatus.OK);
    }
}
