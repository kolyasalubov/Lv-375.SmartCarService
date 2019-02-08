package ua.ita.smartcarservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ua.ita.smartcarservice.dto.bookingDto.WorkerWithSkillDto;
import ua.ita.smartcarservice.dto.stoDto.WorkerDto;
import ua.ita.smartcarservice.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping("/workerbyskill")
    public ResponseEntity<HashMap<String, List<WorkerDto>>> getAllBySkillAndSto(@RequestBody WorkerWithSkillDto
                                                                                        workerWithSkillDto) {
        HashMap<String, List<WorkerDto>> workersBySkillName = new HashMap<>();
        for (String s : workerWithSkillDto.getName()) {
            workersBySkillName.put(s, workerService.findAllWorkersBySkillAndSto(s, workerWithSkillDto.getStoId()));
        }

        return new ResponseEntity<>(workersBySkillName, HttpStatus.OK);

    }
}
