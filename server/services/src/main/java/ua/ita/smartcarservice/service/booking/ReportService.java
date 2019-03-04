package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.entity.booking.ReportEntity;

public interface ReportService {

    ReportEntity addReport(ReportDto reportDto);

}
