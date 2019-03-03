package ua.ita.smartcarservice.controller.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.service.booking.BookingService;
import ua.ita.smartcarservice.service.booking.ReportService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public void saveReport(@RequestBody ReportDto reportDto) {
        long reportId = reportService.addReport(reportDto);
        bookingService.updateReportsId(reportDto, reportId);
    }

    @GetMapping("/progress/{id}")
    public ResponseEntity<List<ReportEntity>> findAllInSto(@PathVariable Long id) {
        List<ReportEntity> cars = reportService.findAllReportsByUserId(id);
        cars.forEach(e -> e.toString());
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
