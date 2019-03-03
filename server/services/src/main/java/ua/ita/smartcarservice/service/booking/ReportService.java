package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.entity.booking.ReportEntity;
import java.util.List;
import ua.ita.smartcarservice.dto.booking.ReportDto;

public interface ReportService {

    List<ReportEntity> findAllReportsByUserId(Long id);

    long addReport(ReportDto reportDto);

}
