package com.smartcarservice.ua.SmartCarService.controller;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.WorkerDto;
import com.smartcarservice.ua.SmartCarService.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping("/workerbyskill")
    public HashMap<String, List<WorkerDto>> getAllBySkill(@RequestParam(value = "name", required = false, defaultValue = "no")
                                                  List<String> name){
        System.out.println(name);
        HashMap<String, List<WorkerDto>> workersBySkillName = new HashMap <>();
        for(String s : name){
            workersBySkillName.put(s, workerService.findAllWorkersBySkill(s));
        }

        return workersBySkillName;
    }
}
