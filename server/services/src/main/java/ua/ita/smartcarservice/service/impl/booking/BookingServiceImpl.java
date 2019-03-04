package ua.ita.smartcarservice.service.impl.booking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.*;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.entity.booking.TimePoint;
import ua.ita.smartcarservice.entity.booking.WorkTime;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.booking.BookingRepository;
import ua.ita.smartcarservice.repository.technicalservice.WorkTypeRepository;
import ua.ita.smartcarservice.repository.technicalservice.WorkersSkillRepository;
import ua.ita.smartcarservice.service.booking.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Integer DAYS_WHEN_WE_FIND_FREE_TIME= 14;
    private static final Integer WORKS_DAY_START_HOURS = 10;
    private static final Integer WORKS_DAY_END_HOURS = 18;
    private static final Integer MINUTES_IN_WORK_DAY = 480;


    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private WorkersSkillRepository workersSkillRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Override
    public List<WorkTimeDto> findAllByWorkerId(Long workerId) {
        return bookingRepository.findAllByWorkerId(workerId).stream()
                .map(this::getWorkTimeDto).collect(Collectors.toList());
    }

    @Override
    public List<WorkTimeDto> findAllByCarId(Long carId) {
        return bookingRepository.findAllByCarId(carId).stream()
                .map(this::getWorkTimeDto).collect(Collectors.toList());
    }

    @Override
    public void addBooking(NewBookingDto newBookingDto) {
        Car car = carRepository.getOne(newBookingDto.getCarId());
        List<WorkTime> bookingToAdd = new ArrayList <>();
        LocalDateTime start = parseDate(newBookingDto.getStart());

        List<Long> worksId = newBookingDto.getWorksInfo().stream().map(WorkInfoDto::getWorkId).collect(Collectors.toList());
        List<Long> workersId =  newBookingDto.getWorkersId().stream().map(Long::valueOf).collect(Collectors.toList());

        List<WorkType> worksTypes = workTypeRepository.findAllById(worksId);

        List<WorkersSkill> workersSkills = workersSkillRepository.findByWorkAndWorker(workersId, worksId);

        newBookingDto.getWorksInfo().forEach(workInfoDto -> {
            WorkTime workTime = new WorkTime();
            workTime.setStartBooking(findTimeToSchedule(start, workInfoDto.getStart()));
            workTime.setEndBooking(findTimeToSchedule(start, workInfoDto.getEnd()));
            workTime.setCar(car);
            workTime.setWorker(
                    userRepository.getOne(searchWorkerToWork(workersSkills, worksTypes, workInfoDto.getWorkId())));
            workTime.setWork(workTypeRepository.getOne(workInfoDto.getWorkId()));

            bookingToAdd.add(workTime);
        });

        bookingRepository.saveAll(bookingToAdd);
    }

    @Override
    public void updateReportsId(ReportDto reportDto, ReportEntity reportEntity){
        bookingRepository.updateReportsId(parseDate(reportDto.getStartTime()),
                parseDate(reportDto.getEndTime()),
                        reportDto.getCarId(),
                        reportEntity);
    }

    @Override
    public List<LocalDateTime> findTimeToBooking(BookingDto bookingDto) {
        List<Long> workersId = bookingDto.getWorkersId().stream().map(Long::valueOf).collect(Collectors.toList());
        LocalDate time = LocalDate.parse(bookingDto.getTime());

        List<WorkTimeDto> timeToWork = findTimeWhenWork(workersId, time, DAYS_WHEN_WE_FIND_FREE_TIME);

        return findFreeTime(findAllTimePoints(timeToWork, time), bookingDto.getNeedTime());

    }


    private List<WorkTimeDto> findTimeWhenWork(List<Long> workersId, LocalDate time, int numberOfDay) {
        LocalDate end = time.plusDays(numberOfDay);
        LocalDateTime startTime = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 10, 0);
        LocalDateTime endTime = LocalDateTime.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth(), 18, 0);

        return bookingRepository.findTimeWhenWork(workersId, startTime, endTime).stream()
                .map(workTime -> (getWorkTimeDto(workTime))).collect(Collectors.toList());
    }

    private List<TimePoint> findAllTimePoints(List<WorkTimeDto> timeToWork, LocalDate time) {
        List<TimePoint> timePoints = new ArrayList <>();

        for (int i = 0; i < DAYS_WHEN_WE_FIND_FREE_TIME; i++) {
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

    private List<LocalDateTime> findFreeTime(List<TimePoint> timePoints, int timeToNeedInMinute) {
        TimePoint start = timePoints.get(0);
        int workNow = -1;
        int remainderOfTime = 0;

        for (int i = 0; i < timePoints.size(); i++) {
            if (timePoints.get(i).isPosition()) {
                if (timePoints.get(i).getTime().getHour() == WORKS_DAY_START_HOURS) {
                    if (remainderOfTime != 0) {
                        int delta = getDeltaTimeInMinute(timePoints.get(i).getTime(), timePoints.get(i+1).getTime());
                        if (delta >= remainderOfTime) {
                            return returnFreeTime(start.getTime(), timePoints.get(i).getTime().plusMinutes(remainderOfTime));
                        } else if (delta < remainderOfTime && timePoints.get(i + 1).getTime().getHour() == WORKS_DAY_END_HOURS) {
                            remainderOfTime -= MINUTES_IN_WORK_DAY;
                        }
                        else {
                            remainderOfTime = 0;
                        }
                    }
                } else {
                        if (getDeltaTimeInMinute(start.getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute
                                && workNow == 0) {
                            return returnFreeTime(start.getTime(), start.getTime().plusMinutes(timeToNeedInMinute));
                        }
                }
                workNow++;
            }
            else {
                if(timePoints.get(i).getTime().getHour() == WORKS_DAY_END_HOURS){
                    if(remainderOfTime == 0 && workNow == 0){
                        if(getDeltaTimeInMinute(timePoints.get(i-1).getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute){
                            return returnFreeTime(timePoints.get(i-1).getTime(), timePoints.get(i-1).getTime().plusMinutes(timeToNeedInMinute));
                        }
                        else {
                            remainderOfTime = timeToNeedInMinute - getDeltaTimeInMinute(timePoints.get(i-1).getTime(), timePoints.get(i).getTime());
                        }
                    }
                }
                else {
                    start = timePoints.get(i);
                }
                workNow--;
            }
        }

        return Collections.EMPTY_LIST;

    }

    private List<LocalDateTime> returnFreeTime(LocalDateTime start, LocalDateTime end){
        List<LocalDateTime> freeTime = new ArrayList <>();
        freeTime.add(start);
        freeTime.add(end);
        return freeTime;
    }

    private LocalDateTime findTimeToSchedule(LocalDateTime time, int requiredTime){

        LocalDateTime end = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 18,0);
        LocalDateTime start = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 10,0).plusDays(1);

        if(getDeltaTimeInMinute(time, end)>=requiredTime){
            return time.plusMinutes(requiredTime);
        }
        else {
            requiredTime-= getDeltaTimeInMinute(time, end);
        }

        return start.plusDays(requiredTime / MINUTES_IN_WORK_DAY)
                .plusMinutes(requiredTime % MINUTES_IN_WORK_DAY);
    }


    private WorkTimeDto getWorkTimeDto(WorkTime workTime) {
        WorkTimeDto workTimeDto = new WorkTimeDto();
        workTimeDto.setStartBooking(workTime.getStartBooking());
        workTimeDto.setEndBooking(workTime.getEndBooking());
        return workTimeDto;
    }

    private int getDeltaTimeInMinute(LocalDateTime first, LocalDateTime second) {
        return (second.getHour() - first.getHour()) * 60 + (second.getMinute() - first.getMinute());
    }

    private Long searchWorkerToWork(List<WorkersSkill> workersSkills, List<WorkType> worksType, Long workId){
        Long skillId = -1L;
        for(WorkType wt : worksType){
            if(wt.getWorkId().equals(workId)){
                skillId = wt.getSkill().getSkillId();
            }
        }

        for (WorkersSkill w : workersSkills){
            if(w.getSkill().getSkillId().equals(skillId)){
                return w.getWorkerId().getId();
            }
        }
        return -1L;
    }

    private LocalDateTime parseDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s.substring(0, s.indexOf('T')) + " " + s.substring(s.indexOf('T') + 1), formatter);
    }

}

