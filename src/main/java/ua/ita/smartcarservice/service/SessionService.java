package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.stoDto.SessionDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface SessionService {

    List<SessionDto> findAllByWorker_WorkerId(Long workerId);

    HashMap<LocalDate, List<SessionDto>> findTimeToBooking(List<Long> workerId, LocalDate time, int timeToNeedInMinute);

    boolean addSession(SessionDto sessionDto, List<Long> workerId, Long CarId);
}
