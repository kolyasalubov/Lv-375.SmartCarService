package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.booking.ReportRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.service.booking.ReportService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    TechnicalServiceRepository technicalServiceRepository;

    public long addReport(ReportDto reportDto){
        return reportRepository.save(repordDtoToEntity(reportDto)).getReportId();
    }

    private ReportEntity repordDtoToEntity(ReportDto reportDto){
        ReportEntity entity = new ReportEntity();
        entity.setCar(carRepository.getCarById(reportDto.getCarId()));
        entity.setTechnicalService(technicalServiceRepository.getByTechnicalServiceId(reportDto.getTechnicalServiceId()));
        entity.setStartTime(parseDateToLocal(reportDto.getStartTime()));
        entity.setEndTime(parseDateToLocal(reportDto.getEndTime()));
        entity.setPrice(reportDto.getPrice());
        return entity;
    }

    private LocalDateTime parseDateToLocal(String strDate) {
        return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

