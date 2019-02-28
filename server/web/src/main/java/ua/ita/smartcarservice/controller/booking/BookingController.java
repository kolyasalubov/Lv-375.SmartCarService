package ua.ita.smartcarservice.controller.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.booking.*;
import ua.ita.smartcarservice.service.booking.BookingService;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TechnicalServiceService technicalService;


    @PostMapping("/api/sessionbyid")
    public ResponseEntity<List<WorkTimeDto>> findAllByWorkerId(@RequestParam(value = "workerId", required = false)
                                                                              String workerId) {
        return new ResponseEntity <>(bookingService.findAllByWorkerId(Long.valueOf(workerId)), HttpStatus.OK);

    }

    @PostMapping("/api/bookingtime")
    public ResponseEntity<BookingInfoDto> findTimeToBooking(@RequestBody BookingDto bookingDto) {
        return new ResponseEntity <>(new BookingInfoDto(bookingService.findTimeToBooking(bookingDto),
                technicalService.findTechnicalServiceByCarId(bookingDto.getCarId())),
                HttpStatus.OK);
    }

    @PostMapping("/api/newbooking")
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