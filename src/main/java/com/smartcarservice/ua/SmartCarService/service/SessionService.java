package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.dto.StoDto.SessionDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Session;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SessionService {

    List<SessionDto> findAllByWorker_WorkerId(Long workerId);

}
