package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.dto.StoDto.SessionDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Session;
import com.smartcarservice.ua.SmartCarService.repository.SessionRepository;
import com.smartcarservice.ua.SmartCarService.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public List<SessionDto> findAllByWorker_WorkerId(Long workerId){
        List<SessionDto> sessionDtos = new ArrayList <>();
        for(Session session : sessionRepository.findAllByWorker_WorkerId(workerId)){
            sessionDtos.add(getSkillDto(session));
        }
        return sessionDtos;
    }

    private SessionDto getSkillDto(Session session){
        SessionDto sessionDto = new SessionDto();
        sessionDto.setStartSession(session.getStartSession());
        sessionDto.setEndSession(session.getEndSession());
        return sessionDto;
    }

}
