package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.booking.ReportRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.service.booking.BookingService;
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
    UserTechnicalServiceRepository userTechnicalServiceRepository;



    public ReportEntity addReport(ReportDto reportDto){
        return reportRepository.save(repordDtoToEntity(reportDto));
    }

    private ReportEntity repordDtoToEntity(ReportDto reportDto){
        ReportEntity entity = new ReportEntity();
        entity.setCar(carRepository.getCarById(reportDto.getCarId()));
        entity.setTechnicalService(userTechnicalServiceRepository
                .findTechnicalServiceByCarId(reportDto.getCarId())
                .getTechnicalServiceId());
        entity.setStartTime(parseDateToLocal(reportDto.getStartTime()));
        entity.setEndTime(parseDateToLocal(reportDto.getEndTime()));
        entity.setRequiredTime(reportDto.getRequiredTime());
        entity.setPrice(reportDto.getPrice());
        return entity;
    }

    private LocalDateTime parseDateToLocal(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s.substring(0, s.indexOf('T')) + " " + s.substring(s.indexOf('T') + 1), formatter);
    }
}

