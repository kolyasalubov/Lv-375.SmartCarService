package ua.ita.smartcarservice.service.impl;

import ua.ita.smartcarservice.dto.bookingDto.BookingDto;
import ua.ita.smartcarservice.dto.bookingDto.NewSessionDto;
import ua.ita.smartcarservice.dto.stoDto.SessionDto;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sto.Session;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.BookingRepository;
import ua.ita.smartcarservice.repository.WorkerRepository;
import ua.ita.smartcarservice.service.BookingService;
import ua.ita.smartcarservice.service.impl.timePoint.TimePoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List <SessionDto> findAllByWorker_WorkerId(Long workerId) {
        List <SessionDto> sessionDtos = new ArrayList <>();
        for (Session session : bookingRepository.findAllByWorker_WorkerId(workerId)) {
            sessionDtos.add(getSessionDto(session));
        }
        return sessionDtos;
    }

    @Override
    public boolean addSession(NewSessionDto newSessionDto) {
        Car car = carRepository.getOne(newSessionDto.getCarId());
        List <Session> sessionsToAdd = new ArrayList <>();
        if (bookingRepository.selectNumberOfSessionWithDate(parseData(newSessionDto.getStarttime()), parseData(newSessionDto.getEndtime())) != 0) {
            return false;
        }
        for (String id : newSessionDto.getWorkerId()) {
            Session session = getEntity(newSessionDto);
            session.setCar(car);
            session.setWorker(workerRepository.getOne(Long.valueOf(id)));
            sessionsToAdd.add(session);
        }

        bookingRepository.saveAll(sessionsToAdd);
        return true;
    }

    @Override
    public HashMap <LocalDate, List <SessionDto>> findTimeToBooking(BookingDto bookingDto) {
        List <Long> workerId = new ArrayList <>();
        Integer timeToNeedInMinute = Integer.parseInt(bookingDto.getNeedTime());
        Integer numberOfDay = Integer.parseInt(bookingDto.getNumberOfDay());
        LocalDate time = LocalDate.parse(bookingDto.getTime());

        for(String s : bookingDto.getWorkerId()){
            workerId.add(Long.valueOf(s));
        }

        List <SessionDto> timeToWork = findTimeWhenWork(workerId, time, numberOfDay);

        return getFreeTimeForEveryDay(timeToWork, time, timeToNeedInMinute, numberOfDay);

    }


    private List <SessionDto> findTimeWhenWork(List <Long> workerId, LocalDate time, int numberOfDay) {
        List <SessionDto> findTimeWhenWork = new ArrayList <>();
        LocalDate end = time.plusDays(numberOfDay);
        LocalDateTime starttime = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 10, 0);
        LocalDateTime endtime = LocalDateTime.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth(), 18, 0);
        for (Long id : workerId) {
            for (Session session : bookingRepository.findTimeWhenWork(id, starttime, endtime)) {
                findTimeWhenWork.add(getSessionDto(session));
            }
        }
        return findTimeWhenWork;
    }

    private HashMap <LocalDate, List <SessionDto>> getFreeTimeForEveryDay(List <SessionDto> timeToWork, LocalDate time, int numberOfDay, int timeToNeed) {
        HashMap <LocalDate, List <SessionDto>> freeTime = new HashMap <>();
        HashMap <LocalDate, List <SessionDto>> bookingShedule = new HashMap <>();
        for(int i = 0; i<timeToNeed;i++){
            freeTime.put(time.plusDays(i), new ArrayList <>());
        }
        for(SessionDto sessionDto : timeToWork){
            List<SessionDto> newList = freeTime.get(getKey(sessionDto.getStartSession()));
            newList.add(sessionDto);
            freeTime.put(getKey(sessionDto.getStartSession()), newList);
        }

        for(LocalDate localDate : freeTime.keySet()){
            bookingShedule.put(localDate, findFreeTime(findAllTimePoints(freeTime.get(localDate), localDate), timeToNeed));
        }

        return bookingShedule;


    }

    private LocalDate getKey(LocalDateTime time){
        return LocalDate.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth());
    }

    private List <TimePoint> findAllTimePoints(List <SessionDto> timeToWork, LocalDate time) {
        List <TimePoint> timePoints = new ArrayList <>();

        Session workDay = new Session();
        workDay.setStartSession(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 10, 0));
        workDay.setEndSession(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 18, 0));

        timePoints.add(new TimePoint(workDay.getStartSession(), true));
        timePoints.add(new TimePoint(workDay.getEndSession(), false));

        for (SessionDto sessionDto : timeToWork) {
            timePoints.add(new TimePoint(sessionDto.getStartSession(), true));
            timePoints.add(new TimePoint(sessionDto.getEndSession(), false));
        }

        Collections.sort(timePoints);
        return timePoints;
    }

    private List <SessionDto> findFreeTime(List <TimePoint> timePoints, int timeToNeedInMinute) {
        List <SessionDto> freeTime = new ArrayList <>();

        TimePoint start = timePoints.get(0);
        int workNow = 0;

        for (int i = 1; i < timePoints.size() - 1; i++) {
            if (timePoints.get(i).isPosition()) {
                if (workNow == 0 && getDeltaTimeInMinut(start.getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute) {
                    SessionDto sessionDto = new SessionDto();
                    sessionDto.setStartSession(start.getTime());
                    sessionDto.setEndSession(timePoints.get(i).getTime());
                    freeTime.add(sessionDto);
                }
                workNow++;

            } else {
                start = timePoints.get(i);
                workNow--;
            }
        }
        if (freeTime.isEmpty() && timePoints.size() == 2) {
            SessionDto sessionDto = new SessionDto();
            sessionDto.setStartSession(start.getTime());
            sessionDto.setEndSession(timePoints.get(timePoints.size() - 1).getTime());
            freeTime.add(sessionDto);
        } else {
            SessionDto sessionDto = new SessionDto();
            if (getDeltaTimeInMinut(timePoints.get(timePoints.size() - 2).getTime(), timePoints.get(timePoints.size() - 1).getTime()) >= timeToNeedInMinute) {
                sessionDto.setStartSession(timePoints.get(timePoints.size() - 2).getTime());
                sessionDto.setEndSession(timePoints.get(timePoints.size() - 1).getTime());
                freeTime.add(sessionDto);
            }
        }

        return freeTime;

    }


    private SessionDto getSessionDto(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setStartSession(session.getStartSession());
        sessionDto.setEndSession(session.getEndSession());
        return sessionDto;
    }

    private int getDeltaTimeInMinut(LocalDateTime first, LocalDateTime second) {
        return (second.getHour() - first.getHour()) * 60 + (second.getMinute() - first.getMinute());
    }

    private Session getEntity(SessionDto sessionDto) {
        Session session = new Session();
        session.setStartSession(sessionDto.getStartSession());
        session.setEndSession(sessionDto.getEndSession());
        return session;
    }

    private Session getEntity(NewSessionDto newSessionDto) {

        Session session = new Session();
        session.setStartSession(parseData(newSessionDto.getStarttime()));
        session.setEndSession(parseData(newSessionDto.getEndtime()));
        return session;
    }

    private LocalDateTime parseData(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s, formatter);
    }

}
