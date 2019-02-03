package com.smartcarservice.ua.SmartCarService.controller;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.TechnicalManagerDto;
import com.smartcarservice.ua.SmartCarService.dto.stoDto.WorkerDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import com.smartcarservice.ua.SmartCarService.service.SkillService;
import com.smartcarservice.ua.SmartCarService.service.TechnicalManagerService;
import com.smartcarservice.ua.SmartCarService.service.TechnicalServiceService;
import com.smartcarservice.ua.SmartCarService.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TechnicalManagerController {

    @Autowired
    TechnicalManagerService technicalManagerService;

    @Autowired
    WorkerService workerService;

    @Autowired
    SkillService skillService;

    @Autowired
    TechnicalServiceService technicalServiceService;

    @PostMapping("/techmanagers")
    @ResponseBody
    TechnicalManager createTechnicalManager(
            @RequestParam(value = "fullname") String fullname,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "serviceId") Long serviceId) {

        TechnicalManager technicalManager = new TechnicalManager();
        TechnicalService technicalService = technicalServiceService.getTechnicalServiceById(serviceId);

        technicalManager.setPassword(password);
        technicalManager.setUserName(username);
        technicalManager.setFullName(fullname);
        technicalManager.setEmail(email);
        technicalManager.setTechnicalService(technicalService);

        technicalManagerService.createTechnicalManager(technicalManager);

        return technicalManager;
    }


    @GetMapping("/techmanagers/{id}")
    @ResponseBody
    TechnicalManagerDto getTechnicalManager(@PathVariable Long id) {
        //ResponseEntity<TechnicalManagerDto> responseEntity;
        return technicalManagerService.getTechnicalManagerDto(id);
    }

    @PutMapping("/techmanagers/{id}/")
    TechnicalManagerDto updateTechnicalManager(@PathVariable Long id,
                                               @RequestParam(value = "fullname", required = false) String fullname,
                                               @RequestParam(value = "username", required = false) String username,
                                               @RequestParam(value = "password", required = false) String password) {
        TechnicalManagerDto managerDto = technicalManagerService.getTechnicalManagerDto(id);

        if (fullname != null) {
            managerDto.setFullName(fullname);
        }

        if (username != null) {
            managerDto.setUserName(username);
        }

        if (password != null) {
            managerDto.setPassword(password);
        }

        technicalManagerService.updateTechnicalManager(id, managerDto);

        return managerDto;
    }

    @GetMapping("/techmanagers/{id}/workers")
    @ResponseBody
    List<WorkerDto> getTechnicalManagerWorkers(@PathVariable Long id) {

        TechnicalManager technicalManager;

        technicalManager = technicalManagerService.getTechnicalManager(id);
        return workerService.findWorkersByTechnicalManager(technicalManager);
    }

    @PostMapping("/techmanagers/{id}/addworker")
    void addWorker(@PathVariable Long id,
                   @RequestParam(value = "fullname") String fullname,
                   @RequestParam(value = "skillId") Long skillId) {

        TechnicalManager technicalManager;
        Skill skill;

        technicalManager = technicalManagerService.getTechnicalManager(id);
        skill = skillService.getSkillById(skillId);

        workerService.saveWorker(fullname, skill, technicalManager);
    }
}
