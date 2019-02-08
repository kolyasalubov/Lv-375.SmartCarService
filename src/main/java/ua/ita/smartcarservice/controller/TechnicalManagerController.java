package ua.ita.smartcarservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

/**
 * Controller (REST) works with TechnicalManager,
 * Technical manager`s TechnicalService and
 * Technical manager`s Workers
 */
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

    /*
    Method for creating TechnicalManager and pushing it into DB,
    it gets parameters from the URL
     */
    @PostMapping("/api/v1/techmanagers")
    @ResponseBody
    TechnicalManager createTechnicalManager(
            @RequestParam(value = "fullname") String fullname,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "serviceId") Long serviceId) {

        return technicalManagerService.createTechnicalManager(fullname, username, email, password, serviceId);
    }

    /*
    Method for getting personal information about TechnicalManager by id
     */
    @GetMapping("/api/v1/techmanagers/{id}")
    @ResponseBody
    //TechnicalManagerDto getTechnicalManager(@PathVariable Long id) {
    ResponseEntity<TechnicalManagerDto> getTechnicalManager(@PathVariable Long id) {
        //ResponseEntity<TechnicalManagerDto> responseEntity;

        TechnicalManagerDto technicalManagerDto;
        ResponseEntity<TechnicalManagerDto> responseEntity;

        try {
            technicalManagerDto = technicalManagerService.getTechnicalManagerDto(id);
            responseEntity = new ResponseEntity<TechnicalManagerDto>(technicalManagerDto, HttpStatus.OK);
        } catch (Exception ex) {
            //TODO Log error
            responseEntity = new ResponseEntity<TechnicalManagerDto>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    /*
    Method for updating TechnicalManager`s personal info,
    it gets parameters from the URL
     */
    @PutMapping("/api/v1/techmanagers/{id}/")
    TechnicalManagerDto updateTechnicalManager(@PathVariable Long id,
                                               @RequestParam(value = "fullname", required = false) String fullname,
                                               @RequestParam(value = "username", required = false) String username,
                                               @RequestParam(value = "password", required = false) String password) {

        return technicalManagerService.updateTechnicalManager(id, fullname, username, password);
    }

    /*
    Method for getting all the Workers of current TechnicalManager by its id
     */
    @GetMapping("/api/v1/techmanagers/{id}/workers")
    @ResponseBody
    //List<WorkerDto> getTechnicalManagerWorkers(@PathVariable Long id) {
    ResponseEntity<List<WorkerDto>> getTechnicalManagerWorkers(@PathVariable Long id) {

        TechnicalManager technicalManager;
        ResponseEntity<List<WorkerDto>> responseEntity;
        try {
            technicalManager = technicalManagerService.getTechnicalManager(id);
            responseEntity = new ResponseEntity<>(workerService.findWorkersByTechnicalManager(technicalManager), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    /*
    Method for creating a new Worker and adding it to current TechnicalManager,
    it gets parameters from the URL
     */
    @PostMapping("/api/v1/techmanagers/{id}/workers")
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

    /*
    Method for deleting Worker by id from DB and current TechnicalManager`s worker list
     */
    @DeleteMapping("/api/v1/techmanagers/{id}/workers/{workerId}")
    void deleteWorker(@PathVariable Long id, @PathVariable Long workerId) throws Exception {
        workerService.deleteWorker(technicalManagerService.getTechnicalManager(id), workerId);
    }
}
