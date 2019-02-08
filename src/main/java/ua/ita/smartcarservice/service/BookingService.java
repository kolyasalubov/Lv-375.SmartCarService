package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.bookingDto.BookingDto;
import ua.ita.smartcarservice.dto.bookingDto.NewSessionDto;
import ua.ita.smartcarservice.dto.stoDto.SessionDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface BookingService {


    List<SessionDto> findAllByWorker_WorkerId(Long workerId);

    HashMap<LocalDate, List<SessionDto>> findTimeToBooking(BookingDto bookingDto);

    boolean addSession(NewSessionDto newSessionDto);

}
