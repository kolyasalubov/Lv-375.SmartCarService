package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.repository.booking.ReportRepository;
import ua.ita.smartcarservice.service.booking.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<ReportEntity> getALlCarInSto(Long id) {
        List<ReportEntity> progres = reportRepository.getALlCarInSto(id);
        return progres;
    }
}

