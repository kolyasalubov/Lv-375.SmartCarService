package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.dto.booking.BookingDto;
import ua.ita.smartcarservice.dto.booking.WorkTimeDto;
import ua.ita.smartcarservice.dto.booking.NewBookingDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BookingService {

    Map<LocalDate, List <WorkTimeDto>> findTimeToBooking(BookingDto bookingDto);

    boolean addBooking(NewBookingDto newSessionDto);

    List <WorkTimeDto> findAllByWorkerId(Long workerId);

    List <WorkTimeDto> findAllByCarId(Long carId);
}
