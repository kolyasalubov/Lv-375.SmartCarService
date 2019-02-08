package ua.ita.smartcarservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.dto.sensors.TireRecordDto;
import ua.ita.smartcarservice.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/record")
public class RecordController {

    @Autowired
    @Qualifier("basic")
    private SensorService sensorService;

    @Autowired
    @Qualifier("tire")
    private SensorService tireService;

    @PostMapping
    public void addRecord(@RequestBody RecordDto recordDto){
        sensorService.addRecord(recordDto);
    }

    @PostMapping("/tire")
    public void addTireRecord(@RequestBody TireRecordDto recordDto){
        tireService.addRecord(recordDto);
    }
}
