package ua.ita.smartcarservice.controller.technicalservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.dto.booking.WorkerWithSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import ua.ita.smartcarservice.dto.technicalservice.WorkerSkillDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.feedback.WorkersRatings;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.feedback.WorkersRatingsService;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.util.HashMap;
import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    UserService userService;

    @Autowired
    WorkerService workerService;

    @Autowired
    WorkersRatingsService workersRatingsService;

    @PostMapping("/api/workerBySkill")
    public ResponseEntity<HashMap<String, List<WorkerDto>>> getAllBySkillAndSto(@RequestBody WorkerWithSkillDto
                                                                                        workerWithSkillDto) {
        HashMap<String, List<WorkerDto>> workersBySkillName = new HashMap<>();
        for (String s : workerWithSkillDto.getName()) {
            workersBySkillName.put(s, workerService.getByUserTechnicalServiceAndWorkersSkill(s, workerWithSkillDto.getSearchId()));
        }

        return new ResponseEntity<>(workersBySkillName, HttpStatus.OK);
    }

    @PostMapping("/api/workerByCar")
    public ResponseEntity<HashMap<String, List<WorkerDto>>> getAllByCarAndSto(@RequestBody WorkerWithSkillDto
                                                                                      workerWithSkillDto) {
        HashMap<String, List<WorkerDto>> workersBySkillName = new HashMap<>();
        for (String s : workerWithSkillDto.getName()) {
            workersBySkillName.put(s, workerService.getByCarIdAndWorkersSkill(s, workerWithSkillDto.getSearchId()));
        }

        return new ResponseEntity<>(workersBySkillName, HttpStatus.OK);
    }

    @GetMapping("/api/v1/workers")
    public ResponseEntity<List<UserEntity>> getAllWorkers() {
        ResponseEntity<List<UserEntity>> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(workerService.getAllWorkers(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @DeleteMapping("/api/v1/workers/{id}")
    public ResponseEntity deleteWorkerById(@PathVariable Long id) {
        ResponseEntity responseEntity;

        try {
            this.workerService.deleteWorker(id);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PostMapping("/api/v1/workers/{id}/rating/{rate}")
    public void addRatingToWorker(@PathVariable Long id, @PathVariable Integer rate) {
        workersRatingsService.addRatingToWorker(id, rate);
    }

    @GetMapping("/api/v1/workers/{id}/rating")
    public ResponseEntity<Double> getWorkersRating(@PathVariable Long id) {
        ResponseEntity<Double> responceEntity;

        try {
            responceEntity = new ResponseEntity<>(workersRatingsService.getAvgWorkersRating(id), HttpStatus.OK);
        } catch (Exception e) {
            responceEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responceEntity;
    }

    @GetMapping("/api/v1/workers/{id}")
    public ResponseEntity<WorkerSkillDto> getWorkerById(@PathVariable Long id) {
        ResponseEntity<WorkerSkillDto> responceEntity;
        WorkerSkillDto workerSkillDto;


        try {
            responceEntity = new ResponseEntity<>(workerService.getWorkerSkillDtoById(id), HttpStatus.OK);
        } catch (Exception e) {
            responceEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responceEntity;
    }
}

