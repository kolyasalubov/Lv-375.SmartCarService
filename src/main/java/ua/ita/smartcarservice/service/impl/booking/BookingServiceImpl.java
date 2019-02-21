package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.BookingDto;
import ua.ita.smartcarservice.dto.booking.WorkTimeDto;
import ua.ita.smartcarservice.dto.booking.NewBookingDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.booking.TimePoint;
import ua.ita.smartcarservice.entity.booking.WorkTime;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.booking.BookingRepository;
import ua.ita.smartcarservice.service.booking.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

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
    public Map <LocalDate, List <WorkTimeDto>> findTimeToBooking(BookingDto bookingDto) {
        List <Long> workerId = new ArrayList <>();
        LocalDate time = LocalDate.parse(bookingDto.getTime());

        bookingDto.getWorkerId().forEach(s -> workerId.add(Long.valueOf(s)));

        List <WorkTimeDto> timeToWork = findTimeWhenWork(workerId, time, bookingDto.getNumberOfDay());

        return getFreeTimeForEveryDay(timeToWork, time, bookingDto.getNumberOfDay(), bookingDto.getNeedTime());

    }


    private List <WorkTimeDto> findTimeWhenWork(List <Long> workerId, LocalDate time, int numberOfDay) {
        List <WorkTimeDto> findTimeWhenWork = new ArrayList <>();
        LocalDate end = time.plusDays(numberOfDay);
        LocalDateTime starttime = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 10, 0);
        LocalDateTime endtime = LocalDateTime.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth(), 18, 0);

        workerId.forEach(id -> {
            bookingRepository.findTimeWhenWork(id, starttime, endtime)
                    .forEach(workTime -> findTimeWhenWork.add(getWorkTimeDto(workTime)));
        });



        return findTimeWhenWork;
    }

    private Map <LocalDate, List <WorkTimeDto>> getFreeTimeForEveryDay(List <WorkTimeDto> timeToWork, LocalDate time, int numberOfDay, int timeToNeed) {
        Map <LocalDate, List <WorkTimeDto>> freeTime = new HashMap <>();
        Map <LocalDate, List <WorkTimeDto>> bookingShedule = new HashMap <>();

        for (int i = 0; i <= numberOfDay; i++) {
            freeTime.put(time.plusDays(i), new ArrayList <>());
        }

        if (!timeToWork.isEmpty()) {
            timeToWork.forEach(workTimeDto -> {
                List <WorkTimeDto> newList = freeTime.get(getKey(workTimeDto.getStartBooking()));
                newList.add(workTimeDto);
                freeTime.put(getKey(workTimeDto.getStartBooking()), newList);
            });
        }

        freeTime.keySet().forEach(localDate -> bookingShedule.put(localDate, findFreeTime(findAllTimePoints(freeTime.get(localDate), localDate), timeToNeed)));

        return bookingShedule;


    }

    private LocalDate getKey(LocalDateTime time) {
        return LocalDate.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth());
    }

    private List <TimePoint> findAllTimePoints(List <WorkTimeDto> timeToWork, LocalDate time) {
        List <TimePoint> timePoints = new ArrayList <>();

        WorkTime workDay = new WorkTime();
        workDay.setStartBooking(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 10, 0));
        workDay.setEndBooking(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 18, 0));

        timePoints.add(new TimePoint(workDay.getStartBooking(), true));
        timePoints.add(new TimePoint(workDay.getEndBooking(), false));

        timeToWork.forEach(workTimeDto -> {
            timePoints.add(new TimePoint(workTimeDto.getStartBooking(), true));
            timePoints.add(new TimePoint(workTimeDto.getEndBooking(), false));
        });

        Collections.sort(timePoints);
        return timePoints;
    }

    private List <WorkTimeDto> findFreeTime(List <TimePoint> timePoints, int timeToNeedInMinute) {
        List <WorkTimeDto> freeTime = new ArrayList <>();

        TimePoint start = timePoints.get(0);
        int workNow = 0;

        for (int i = 1; i < timePoints.size() - 1; i++) {
            if (timePoints.get(i).isPosition()) {
                if (workNow == 0 && getDeltaTimeInMinut(start.getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute) {
                    WorkTimeDto workTimeDto = new WorkTimeDto();
                    workTimeDto.setStartBooking(start.getTime());
                    workTimeDto.setEndBooking(timePoints.get(i).getTime());
                    freeTime.add(workTimeDto);
                }
                workNow++;

            } else {
                start = timePoints.get(i);
                workNow--;
            }
        }
        if (freeTime.isEmpty() && timePoints.size() == 2
                && getDeltaTimeInMinut(timePoints.get(timePoints.size() - 2).getTime(), timePoints.get(timePoints.size() - 1).getTime()) >= timeToNeedInMinute) {
            WorkTimeDto workTimeDto = new WorkTimeDto();
            workTimeDto.setStartBooking(start.getTime());
            workTimeDto.setEndBooking(timePoints.get(timePoints.size() - 1).getTime());
            freeTime.add(workTimeDto);
        } else {
            WorkTimeDto workTimeDto = new WorkTimeDto();
            if (getDeltaTimeInMinut(timePoints.get(timePoints.size() - 2).getTime(), timePoints.get(timePoints.size() - 1).getTime()) >= timeToNeedInMinute) {
                workTimeDto.setStartBooking(timePoints.get(timePoints.size() - 2).getTime());
                workTimeDto.setEndBooking(timePoints.get(timePoints.size() - 1).getTime());
                freeTime.add(workTimeDto);
            }
        }

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

    private WorkTime getEntity(WorkTimeDto workTimeDto) {
        WorkTime workTime = new WorkTime();
        workTime.setStartBooking(workTimeDto.getStartBooking());
        workTime.setEndBooking(workTimeDto.getEndBooking());
        return workTime;
    }

    private WorkTime getEntity(NewBookingDto newBookingDto) {

        WorkTime newBooking = new WorkTime();
        newBooking.setStartBooking(parseFromFront(newBookingDto.getStart()));
        newBooking.setEndBooking(parseFromFront(newBookingDto.getStart()).plusHours(newBookingDto.getRequiredTime()));
        return newBooking;
    }

    private LocalDateTime parseDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s, formatter);
    }

    private LocalDateTime parseFromFront(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s.substring(0, s.indexOf('T')) + " " + s.substring(s.indexOf('T') + 1), formatter);
    }

}

