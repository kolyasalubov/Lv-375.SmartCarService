package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.dto.booking.ReportExtendedDto;
import ua.ita.smartcarservice.entity.booking.ReportEntity;

import java.util.List;

public interface ReportService {

    long addReport(ReportDto reportDto);

    ReportEntity findReportById(long reportId);

    List<ReportEntity> findAllReportsByUserId(long userId);

    ReportExtendedDto formExtendedReport(long reportId);

}
