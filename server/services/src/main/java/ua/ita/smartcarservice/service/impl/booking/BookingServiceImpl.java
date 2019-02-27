package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.BookingDto;
import ua.ita.smartcarservice.dto.booking.NewBookingDto;
import ua.ita.smartcarservice.dto.booking.WorkTimeDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.booking.TimePoint;
import ua.ita.smartcarservice.entity.booking.WorkTime;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.booking.BookingRepository;
//import ua.ita.smartcarservice.repository.impl.WorkTimeRepositoryImpl;
import ua.ita.smartcarservice.service.booking.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Integer NUMBER_OF_DAY = 14;
    private static final Integer DAY_START = 10;
    private static final Integer DAY_END = 18;
    private static final Integer MINUTE_IN_WORK_DAY = 480;
    private static final LocalDateTime DEFAULT_TIME = LocalDateTime.of(2000, 1, 1, 0, 0);


    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

//    @Autowired
//    private WorkTimeRepositoryImpl workTimeRepository;

    @Override
    public List <WorkTimeDto> findAllByWorkerId(Long workerId) {
        List <WorkTimeDto> workDtos = new ArrayList <>();
        bookingRepository.findAllByWorkerId(workerId).forEach(workTime -> workDtos.add(getWorkTimeDto(workTime)));
        return workDtos;
    }

    @Override
    public List <WorkTimeDto> findAllByCarId(Long carId) {
        List <WorkTimeDto> workDtos = new ArrayList <>();
        bookingRepository.findAllByCarId(carId).forEach(workTime -> workDtos.add(getWorkTimeDto(workTime)));
        return workDtos;
    }

    @Override
    public boolean addBooking(NewBookingDto newBookingDto) {
        Car car = carRepository.getOne(newBookingDto.getCarId());
        List <WorkTime> bookingToAdd = new ArrayList <>();
        LocalDateTime end = parseFromFront(newBookingDto.getStart()).plusHours(newBookingDto.getRequiredTime());

        if (bookingRepository.selectNumberOfBookingWithDate(parseFromFront(newBookingDto.getStart()), end) != 0
                && LocalDateTime.now().compareTo(parseFromFront(newBookingDto.getStart())) < 0) {
            return false;
        }

        newBookingDto.getWorkerId().forEach(id -> {
            WorkTime newBooking = getEntity(newBookingDto);
            newBooking.setCar(car);
            newBooking.setWorker(userRepository.getOne(Long.valueOf(id)));
            bookingToAdd.add(newBooking);
        });

        bookingRepository.saveAll(bookingToAdd);
        return true;
    }

    @Override
    public List<LocalDateTime> findTimeToBooking(BookingDto bookingDto) {
//        List <Long> workerId = new ArrayList <>();
//        LocalDate time = LocalDate.parse(bookingDto.getTime());
//
//        bookingDto.getWorkerId().forEach(s -> workerId.add(Long.valueOf(s)));
//
//        List <WorkTimeDto> timeToWork = findTimeWhenWork(workerId, time, NUMBER_OF_DAY);
//
//        return findFreeTime(findAllTimePoints(timeToWork, time), bookingDto.getNeedTime());
        return null;

    }


    private List <WorkTimeDto> findTimeWhenWork(List <Long> workerId, LocalDate time, int numberOfDay) {
//        List <WorkTimeDto> findTimeWhenWork = new ArrayList <>();
//        LocalDate end = time.plusDays(numberOfDay);
//        LocalDateTime starttime = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 10, 0);
//        LocalDateTime endtime = LocalDateTime.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth(), 18, 0);
//
//        workTimeRepository.getAllBookingById(workerId, starttime, endtime)
//                .forEach(workTime -> findTimeWhenWork.add(getWorkTimeDto(workTime)));
//
//      return findTimeWhenWork;
        return null;
    }

    private List <TimePoint> findAllTimePoints(List <WorkTimeDto> timeToWork, LocalDate time) {
        List <TimePoint> timePoints = new ArrayList <>();

        for (int i = 0; i < NUMBER_OF_DAY; i++) {
            timePoints.add(new TimePoint(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 10, 0).plusDays(i), true));
            timePoints.add(new TimePoint(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 18, 0).plusDays(i), false));
        }

        timeToWork.forEach(workTimeDto -> {
            timePoints.add(new TimePoint(workTimeDto.getStartBooking(), true));
            timePoints.add(new TimePoint(workTimeDto.getEndBooking(), false));
        });

        Collections.sort(timePoints);
        return timePoints;
    }

    private List<LocalDateTime> findFreeTime(List <TimePoint> timePoints, int timeToNeedInMinute) {

        List<LocalDateTime> freetime = new ArrayList <>();

        TimePoint start = timePoints.get(0);
        int workNow = -1;
        int remainderOfTime = 0;

        for (int i = 0; i < timePoints.size(); i++) {
            if (timePoints.get(i).isPosition()) {
                if (timePoints.get(i).getTime().getHour() == DAY_START) {
                    if (remainderOfTime != 0) {
                        int delta = getDeltaTimeInMinut(timePoints.get(i).getTime(), timePoints.get(i+1).getTime());

                        if (delta >= remainderOfTime) {
                            return returnFreeTime(start.getTime(), timePoints.get(i).getTime().plusMinutes(remainderOfTime));
                        } else if (delta < remainderOfTime && timePoints.get(i + 1).getTime().getHour() == DAY_END) {
                            remainderOfTime -= MINUTE_IN_WORK_DAY;
                        }
                        else {
                            remainderOfTime = 0;
                        }
                    }
                } else {
                        if (getDeltaTimeInMinut(start.getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute
                                && workNow == 0) {
                            return returnFreeTime(start.getTime(), start.getTime().plusMinutes(timeToNeedInMinute));
                        }
                }
                workNow++;
            }
            else {
                if(timePoints.get(i).getTime().getHour() == DAY_END){
                    if(remainderOfTime == 0 && workNow == 0){
                        if(getDeltaTimeInMinut(timePoints.get(i-1).getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute){
                            return returnFreeTime(timePoints.get(i-1).getTime(), timePoints.get(i-1).getTime().plusMinutes(timeToNeedInMinute));
                        }
                        else {
                            remainderOfTime = timeToNeedInMinute - getDeltaTimeInMinut(timePoints.get(i-1).getTime(), timePoints.get(i).getTime());
                        }
                    }
                }
                else {
                    start = timePoints.get(i);
                }
                workNow--;
            }
        }

        return freetime;

    }

    private List<LocalDateTime> returnFreeTime(LocalDateTime start, LocalDateTime end){
        List<LocalDateTime> freeTime = new ArrayList <>();
        freeTime.add(start);
        freeTime.add(end);
        return freeTime;
    }


    private WorkTimeDto getWorkTimeDto(WorkTime workTime) {
        WorkTimeDto workTimeDto = new WorkTimeDto();
        workTimeDto.setStartBooking(workTime.getStartBooking());
        workTimeDto.setEndBooking(workTime.getEndBooking());
        return workTimeDto;
    }

    private int getDeltaTimeInMinut(LocalDateTime first, LocalDateTime second) {
        return (second.getHour() - first.getHour()) * 60 + (second.getMinute() - first.getMinute());
    }

    private WorkTime getEntity(NewBookingDto newBookingDto) {

        WorkTime newBooking = new WorkTime();
        newBooking.setStartBooking(parseFromFront(newBookingDto.getStart()));
        newBooking.setEndBooking(parseFromFront(newBookingDto.getStart()).plusHours(newBookingDto.getRequiredTime()));
        return newBooking;
    }

    private LocalDateTime parseFromFront(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s.substring(0, s.indexOf('T')) + " " + s.substring(s.indexOf('T') + 1), formatter);
    }

}

