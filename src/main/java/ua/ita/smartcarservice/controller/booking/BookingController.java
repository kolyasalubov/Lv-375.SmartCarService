package ua.ita.smartcarservice.controller.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.booking.BookingDto;
import ua.ita.smartcarservice.dto.booking.NewBookingDto;
import ua.ita.smartcarservice.dto.booking.SkillNameDto;
import ua.ita.smartcarservice.dto.booking.WorkTimeDto;
import ua.ita.smartcarservice.service.booking.BookingService;
import ua.ita.smartcarservice.service.booking.SkillDependencyService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/api/v1/sessionbyid")
    public ResponseEntity<List<WorkTimeDto>> findAllByWorker_WorkerId(@RequestParam(value = "workerId", required = false)
                                                                                String workerId) {
        return new ResponseEntity <>(bookingService.findAllByWorkerId(Long.valueOf(workerId)), HttpStatus.OK);

    }

    @PostMapping("/api/v1/bookingtime")
    public ResponseEntity<Map<LocalDate, List <WorkTimeDto>>> findTimeToBooking(@RequestBody BookingDto bookingDto) {
        return new ResponseEntity <>(bookingService.findTimeToBooking(bookingDto), HttpStatus.OK);
    }

    @PostMapping("/api/v1/newbooking")
    public ResponseEntity<HttpStatus> addSession(@RequestBody NewBookingDto newBookingDto) {
        return bookingService.addBooking(newBookingDto)
                ? new ResponseEntity <>(HttpStatus.OK)
                : new ResponseEntity <>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping ("/api/sessionbycarid")
    public ResponseEntity<List <WorkTimeDto>> findAllByCarId(@RequestParam(value = "carId")
                                                                                Long carId) {
        return new ResponseEntity <>(bookingService.findAllByCarId(carId), HttpStatus.OK);

    }
}