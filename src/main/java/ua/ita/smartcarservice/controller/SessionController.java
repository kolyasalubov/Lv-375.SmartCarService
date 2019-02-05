package ua.ita.smartcarservice.controller;

import ua.ita.smartcarservice.dto.stoDto.SessionDto;
import ua.ita.smartcarservice.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/sessionbyid")
    public List<SessionDto> findAllByWorker_WorkerId(@RequestParam(value = "workerId", required = false)
                                                              String workerId){
        return sessionService.findAllByWorker_WorkerId(Long.valueOf(workerId));

    }

    @PostMapping("/bookingtime")
    public HashMap<LocalDate, List<SessionDto>> findTimeToBooking(@RequestParam(value = "workersId", required = false)
                                                        List<String> workersId, @RequestParam(value = "time", required = false)
                                                                      String time, @RequestParam(value = "timeToNeedInMinute", required = false)
                                                                      String timeToNeedInMinute){
        List<Long> id = new ArrayList <>();
        for(String s : workersId){
            id.add(Long.valueOf(s));
        }
        return sessionService.findTimeToBooking(id, LocalDate.parse(time), Integer.valueOf(timeToNeedInMinute));

    }

    @PostMapping("/addsession")
    public ResponseEntity<HttpStatus> addSession(@RequestParam(value = "workerId", required = false) List<String> workerId,
                                                 @RequestParam(value = "starttime", required = false) String starttime,
                                                 @RequestParam(value = "endtime", required = false) String endtime,
                                                 @RequestParam(value = "carId", required = false) String carId){
        SessionDto sessionDto = new SessionDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        sessionDto.setStartSession(LocalDateTime.parse(starttime, formatter));
        sessionDto.setEndSession(LocalDateTime.parse(endtime, formatter));

        List<Long> workersId = new ArrayList <>();
        for(String id : workerId){
            workersId.add(Long.valueOf(id));
        }

        if(sessionService.addSession(sessionDto, workersId, Long.valueOf(carId))){
            return new ResponseEntity <>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity <>(HttpStatus.BAD_REQUEST);
        }
    }
}
