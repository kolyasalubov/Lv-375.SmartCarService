package ua.ita.smartcarservice.controller.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.service.SensorService;

@RestController
@RequestMapping(value = "/api/record")
public class RecordController {

    @Autowired
    private SensorService sensorService;

    @PostMapping
    public void addRecord(@RequestBody RecordDto recordDto) {
        sensorService.addRecord(recordDto);
    }

}