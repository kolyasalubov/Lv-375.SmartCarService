package ua.ita.smartcarservice.controller;

import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecordController {

    @Autowired
    private SensorService sensorService;

    @PostMapping("/record")
    public void addRecord(@RequestBody RecordDto recordDto){
        sensorService.addRecord(recordDto);
    }
}
