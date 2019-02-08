package ua.ita.smartcarservice.serviceImpl;

import ua.ita.smartcarservice.dto.stoDto.SessionDto;
import ua.ita.smartcarservice.entity.sto.Session;
import ua.ita.smartcarservice.repository.SessionRepository;
import ua.ita.smartcarservice.service.SessionService;
import ua.ita.smartcarservice.serviceImpl.timePoint.TimePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public List<SessionDto> findAllByWorker_WorkerId(Long workerId){
        List<SessionDto> sessionDtos = new ArrayList <>();
        for(Session session : sessionRepository.findAllByWorker_WorkerId(workerId)){
            sessionDtos.add(getSessionDto(session));
        }
        return sessionDtos;
    }

    @Override
    public HashMap<LocalDate, List<SessionDto>> findTimeToBooking(List<Long> workerId, LocalDate time, int timeToNeedInMinute){
        HashMap<LocalDate, List<SessionDto>> freeTime = new HashMap <>();
        for(int i = 0; i < 7 ; i++) {
            freeTime.put(time, findFreeTime(findAllTimePoints(findTimeWhenWork(workerId, time), time), timeToNeedInMinute));
            time = time.plusDays(1);
        }
        return freeTime;

    }



    private List<SessionDto> findTimeWhenWork(List<Long> workerId, LocalDate time){
        List<SessionDto> findTimeWhenWork = new ArrayList <>();
        for(Long id : workerId){
            for(Session session : sessionRepository.findTimeWhenWork(id, time)){
                findTimeWhenWork.add(getSessionDto(session));
            }
        }
        return findTimeWhenWork;
    }

    private List<TimePoint> findAllTimePoints(List<SessionDto> timeToWork, LocalDate time){
        List<TimePoint> timePoints = new ArrayList <>();

        Session workDay = new Session();
        workDay.setStartSession(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 10, 0));
        workDay.setEndSession(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 18, 0));

        timePoints.add(new TimePoint(workDay.getStartSession(), true));
        timePoints.add(new TimePoint(workDay.getEndSession(), false));

        for(SessionDto sessionDto : timeToWork){
            timePoints.add(new TimePoint(sessionDto.getStartSession(), true));
            timePoints.add(new TimePoint(sessionDto.getEndSession(), false));
        }

        Collections.sort(timePoints);
        return timePoints;
    }

    private List<SessionDto> findFreeTime(List<TimePoint> timePoints, int timeToNeedInMinute){
        List<SessionDto> freeTime = new ArrayList <>();

        TimePoint start = timePoints.get(0);
        int workNow = 0;

        for(int i = 1; i<timePoints.size() - 1; i++){
            if(timePoints.get(i).isPosition()){
                if(workNow == 0 && getDeltaTimeInMinut(start.getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute){
                    SessionDto sessionDto = new SessionDto();
                    sessionDto.setStartSession(start.getTime());
                    sessionDto.setEndSession(timePoints.get(i).getTime());
                    freeTime.add(sessionDto);
                }
                workNow ++;

            }
            else {
                start = timePoints.get(i);
                workNow --;
            }
        }
        if(freeTime.isEmpty()){
            SessionDto sessionDto = new SessionDto();
            sessionDto.setStartSession(start.getTime());
            sessionDto.setEndSession(timePoints.get(timePoints.size() - 1).getTime());
            freeTime.add(sessionDto);
        }
        else {
            SessionDto sessionDto = new SessionDto();
            if(getDeltaTimeInMinut(timePoints.get(timePoints.size() - 2).getTime(), timePoints.get(timePoints.size() - 1).getTime())>=timeToNeedInMinute) {
                sessionDto.setStartSession(timePoints.get(timePoints.size() - 2).getTime());
                sessionDto.setEndSession(timePoints.get(timePoints.size() - 1).getTime());
                freeTime.add(sessionDto);
            }
        }

        return freeTime;

    }








    private SessionDto getSessionDto(Session session){
        SessionDto sessionDto = new SessionDto();
        sessionDto.setStartSession(session.getStartSession());
        sessionDto.setEndSession(session.getEndSession());
        return sessionDto;
    }

    private int getDeltaTimeInMinut(LocalDateTime first, LocalDateTime second){
        return (second.getHour() - first.getHour()) * 60 + (second.getMinute() - first.getMinute());
    }

}
