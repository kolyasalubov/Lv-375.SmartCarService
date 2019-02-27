package ua.ita.smartcarservice.controller.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.alerts.FaultCodeDto;
import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.alerts.FaultCode;
import ua.ita.smartcarservice.entity.sensors.common.SensorTypes;
import ua.ita.smartcarservice.service.CarService;
import ua.ita.smartcarservice.service.SensorService;
import ua.ita.smartcarservice.service.alerts.FaultCodeService;
import ua.ita.smartcarservice.service.alerts.NotificationService;

@RestController
@RequestMapping(value = "/api/record")
public class RecordController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private FaultCodeService faultCodeService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CarService carService;

    @PostMapping
    public void addRecord(@RequestBody RecordDto recordDto) {
        sensorService.addRecord(recordDto);
        saveNotification(recordDto);
    }

    /* Helper method to analyze sensors data and save notification for user */
    private void saveNotification(RecordDto recordDto) {
        String sensorType = recordDto.getSensorType();
        Double currentValue = (Double)recordDto.getValues().values().toArray()[0];
        if(analyzeSensorData(sensorType, currentValue)) {
            RecordDto previousRecord = sensorService.findRecordBeforeDate(recordDto);
            FaultCodeDto code = faultCodeService.getFaultCode(sensorType);
            CarDto car = carService.findByVin(recordDto.getCarVin());
            notificationService.saveNotification(new NotificationsDto(code, car));
        }
    }

    /* Helper method to analyze sensors data */
    private boolean analyzeSensorData(String type, Double value) {
        if(type.equals(SensorTypes.FUEL.toString()) && value < 10) {
            return true;
        } else if(type.equals(SensorTypes.GLASS_WASHER_FLUID.toString()) && value < 0.5){
            return true;
        } else if(type.equals(SensorTypes.COOLANT.toString()) && value < 1) {
            return true;
        }
        return false;
    }
}
