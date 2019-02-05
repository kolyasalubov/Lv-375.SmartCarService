package com.smartcarservice.ua.SmartCarService.controller;

import com.smartcarservice.ua.SmartCarService.dto.sensors.RecordDto;
import com.smartcarservice.ua.SmartCarService.dto.stoDto.SkillDto;
import com.smartcarservice.ua.SmartCarService.entity.sensors.data.SpeedEntity;
import com.smartcarservice.ua.SmartCarService.service.SensorService;
import com.smartcarservice.ua.SmartCarService.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private SensorService sensorService;

    @PostMapping("/record")
    public void addRecord(@RequestBody RecordDto recordDto){
        sensorService.addRecord(recordDto);
    }
}
