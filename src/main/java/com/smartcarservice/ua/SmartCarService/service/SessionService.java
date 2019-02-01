package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.SessionDto;

import java.util.List;

public interface SessionService {

    List<SessionDto> findAllByWorker_WorkerId(Long workerId);

}
