package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.entity.booking.ReportEntity;

import java.util.List;

public interface ReportService {

    List<ReportEntity> getALlCarInSto(Long id);

}
