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
    public HashMap<String, List<WorkerDto>> getAllBySkillAndSto(@RequestParam(value = "name", required = false, defaultValue = "no")
                                                  List<String> name, @RequestParam(value = "stoId", required = false) String stoId){
        System.out.println(name);
        HashMap<String, List<WorkerDto>> workersBySkillName = new HashMap <>();
        for(String s : name){
            workersBySkillName.put(s, workerService.findAllWorkersBySkillAndSto(s, Long.valueOf(stoId)));
        }

        return workersBySkillName;
    }
}
