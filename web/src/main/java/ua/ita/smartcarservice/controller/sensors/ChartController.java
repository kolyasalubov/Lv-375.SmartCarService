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
import ua.ita.smartcarservice.service.sensors.SensorService;

@RestController
@RequestMapping(value = "/api/v1/chart")
public class ChartController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/day")
    public ResponseEntity<ChartDto> getAllByDay(@RequestParam(value = "sensorType") String sensorType,
                                                @RequestParam(value = "carId") Long carId,
                                                @RequestParam(value = "date") String date) {
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(sensorService.getAllByDay(dateForChartDto));
    }

    @GetMapping("/month")
    public ResponseEntity<ChartDto> getAllByMonth(@RequestParam(value = "sensorType") String sensorType,
                                                   @RequestParam(value = "carId") Long carId,
                                                   @RequestParam(value = "date") String date) {
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(sensorService.getAvgByMonth(dateForChartDto));
    }

    @GetMapping("/month/min")
    public ResponseEntity<ChartDto> getMinByMonth(@RequestParam(value = "sensorType") String sensorType,
                                                   @RequestParam(value = "carId") Long carId,
                                                   @RequestParam(value = "date") String date) {
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(sensorService.getMinByMonth(dateForChartDto));
    }

    @GetMapping("/month/max")
    public ResponseEntity<ChartDto> getMaxByMonth(@RequestParam(value = "sensorType") String sensorType,
                                                   @RequestParam(value = "carId") Long carId,
                                                   @RequestParam(value = "date") String date) {
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(sensorService.getMaxByMonth(dateForChartDto));
    }

    @GetMapping("/year")
    public ResponseEntity<ChartDto> getAllByYear(@RequestParam(value = "sensorType") String sensorType,
                                                  @RequestParam(value = "carId") Long carId,
                                                  @RequestParam(value = "date") String date) {
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(sensorService.getAvgByYear(dateForChartDto));
    }

    @GetMapping("/year/min")
    public ResponseEntity<ChartDto> getMinByYear(@RequestParam(value = "sensorType") String sensorType,
                                                  @RequestParam(value = "carId") Long carId,
                                                  @RequestParam(value = "date") String date) {
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(sensorService.getMinByYear(dateForChartDto));
    }

    @GetMapping("/year/max")
    public ResponseEntity<ChartDto> getMaxByYear(@RequestParam(value = "sensorType") String sensorType,
                                                  @RequestParam(value = "carId") Long carId,
                                                  @RequestParam(value = "date") String date) {
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(sensorService.getMaxByYear(dateForChartDto));
    }

    private ResponseEntity<ChartDto> getResponse(ChartDto chartDto) {
        return (chartDto.dataSize() == 0) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(chartDto, HttpStatus.OK);
    }
}
