package ua.ita.smartcarservice.controller.technicalservice;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.dto.booking.WorkerBySkillNameDto;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.dto.booking.WorkerWithSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import ua.ita.smartcarservice.dto.technicalservice.WorkerSkillDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.feedback.WorkersRatings;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.booking.WorkDependencyService;
import ua.ita.smartcarservice.service.feedback.WorkersRatingsService;
import ua.ita.smartcarservice.service.impl.booking.WorkInfo;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class WorkerController {

    @Autowired
    UserService userService;

    @Autowired
    WorkerService workerService;

    @Autowired
    WorkersRatingsService workersRatingsService;

    @Autowired
    private WorkDependencyService workDependencyService;

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    @PostMapping("/api/workers/skill")
    public ResponseEntity<WorkerBySkillNameDto> findAllByCarAndSto(@RequestBody WorkerWithSkillDto
                                                                            workerWithSkillDto) {

        WorkInfo workInfo = workDependencyService.findWorkInfo(workerWithSkillDto.getWorksName());

        Map<String, List<WorkerDto>> workersBySkillName = workerWithSkillDto.getSkillsName().stream().collect(
                Collectors.toMap(s -> s,
                        s -> workerService.findByCarIdAndWorkersSkill(s, workerWithSkillDto.getCarId()))
        );

        WorkerBySkillNameDto workerBySkillNameDto = new WorkerBySkillNameDto();
        workerBySkillNameDto.setWorkersList(workersBySkillName);
        workerBySkillNameDto.setRequiredTime(workInfo.getRequiredTime());
        workerBySkillNameDto.setWorksInfo(workInfo.getWorkInfo());

        return new ResponseEntity <>(workerBySkillNameDto, HttpStatus.OK);
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
            logger.info("Trying to delete worker by id: " + id);
            this.workerService.deleteWorker(id);
            logger.info("Successfully deleted worker by id: " + id);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            logger.error("Can not delete worker by id: " + id + " Ex:" + e.getMessage());
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
        ResponseEntity<WorkerSkillDto> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(workerService.getWorkerSkillDtoById(id), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
}

