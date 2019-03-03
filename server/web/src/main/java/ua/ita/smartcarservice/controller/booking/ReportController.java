package ua.ita.smartcarservice.controller.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.service.booking.BookingService;
import ua.ita.smartcarservice.service.booking.ReportService;

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
}
