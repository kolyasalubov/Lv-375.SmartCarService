package ua.ita.smartcarservice.controller;

import ua.ita.smartcarservice.dto.stoDto.TechnicalManagerDto;
import ua.ita.smartcarservice.dto.stoDto.WorkerDto;
import ua.ita.smartcarservice.entity.sto.Skill;
import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
import ua.ita.smartcarservice.service.SkillService;
import ua.ita.smartcarservice.service.TechnicalManagerService;
import ua.ita.smartcarservice.service.TechnicalServiceService;
import ua.ita.smartcarservice.service.WorkerService;
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

    @PostMapping("/api/techmanagers")
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


    @GetMapping("/api/techmanagers/{id}")
    @ResponseBody
    TechnicalManagerDto getTechnicalManager(@PathVariable Long id) {
        //ResponseEntity<TechnicalManagerDto> responseEntity;
        return technicalManagerService.getTechnicalManagerDto(id);
    }

    @PutMapping("/api/techmanagers/{id}/")
    TechnicalManagerDto updateTechnicalManager(@PathVariable Long id,
                                               @RequestParam(value = "fullname", required = false) String fullname,
                                               @RequestParam(value = "username", required = false) String username,
                                               @RequestParam(value = "password", required = false) String password) {
        TechnicalManagerDto managerDto = technicalManagerService.getTechnicalManagerDto(id);

        managerDto.setId(id);

        if (fullname != null) {
            managerDto.setFullName(fullname);
        }

        if (username != null) {
            managerDto.setUserName(username);
        }

        if (password != null) {
            managerDto.setPassword(password);
        }

        technicalManagerService.updateTechnicalManager(managerDto);

        return managerDto;
    }

    @GetMapping("/api/techmanagers/{id}/workers")
    @ResponseBody
    List<WorkerDto> getTechnicalManagerWorkers(@PathVariable Long id) {

        TechnicalManager technicalManager;

        technicalManager = technicalManagerService.getTechnicalManager(id);
        return workerService.findWorkersByTechnicalManager(technicalManager);
    }

    @PostMapping("/api/techmanagers/{id}/workers")
    void addTechnicalManagerWorker(@PathVariable Long id,
                   @RequestParam(value = "fullname") String fullname,
                   @RequestParam(value = "skillid") Long skillId) {

        TechnicalManager technicalManager;
        TechnicalService technicalService;
        Skill skill;

        technicalManager = technicalManagerService.getTechnicalManager(id);
        technicalService = technicalServiceService.getByTechnicalManager(technicalManager);
        skill = skillService.getSkillById(skillId);

        workerService.saveWorker(fullname, skill, technicalManager, technicalService);
    }

    @DeleteMapping("/api/techmanagers/{id}/workers/{workerId}")
    void deleteWorker(@PathVariable Long id, @PathVariable Long workerId) throws Exception{
        workerService.deleteWorker(technicalManagerService.getTechnicalManager(id), workerId);
    }
}
