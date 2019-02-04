package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.SessionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface SessionService {

    List<SessionDto> findAllByWorker_WorkerId(Long workerId);

    HashMap<LocalDate, List<SessionDto>> findTimeToBooking(List<Long> workerId, LocalDate time, int timeToNeedInMinute);

}
