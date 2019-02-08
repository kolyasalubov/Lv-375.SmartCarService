package ua.ita.smartcarservice.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ua.ita.smartcarservice.dto.bookingDto.BookingDto;
import ua.ita.smartcarservice.dto.bookingDto.NewSessionDto;
import ua.ita.smartcarservice.dto.stoDto.SessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.service.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/sessionbyid")
    public ResponseEntity<List<SessionDto>> findAllByWorker_WorkerId(@RequestParam(value = "workerId", required = false)
                                                                             String workerId){
        return new ResponseEntity <>(bookingService.findAllByWorker_WorkerId(Long.valueOf(workerId)), HttpStatus.OK);

    }

    @PostMapping("/bookingtime")
    public ResponseEntity<HashMap<LocalDate, List<SessionDto>>> findTimeToBooking(@RequestBody BookingDto bookingDto){
        return new ResponseEntity <>(bookingService.findTimeToBooking(bookingDto), HttpStatus.OK);
    }

    @PostMapping("/addsession")
    public ResponseEntity<HttpStatus> addSession(@RequestBody NewSessionDto newSessionDto){
        if(bookingService.addSession(newSessionDto)){
            return new ResponseEntity <>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity <>(HttpStatus.BAD_REQUEST);
        }
    }
}
