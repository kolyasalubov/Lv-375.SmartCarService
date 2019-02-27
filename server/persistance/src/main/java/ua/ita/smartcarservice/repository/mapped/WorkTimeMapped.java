package ua.ita.smartcarservice.repository.mapped;

import org.springframework.jdbc.core.RowMapper;
import ua.ita.smartcarservice.entity.booking.WorkTime;

import java.sql.ResultSet;
import java.sql.SQLException;


public class WorkTimeMapped implements RowMapper<WorkTime> {

    public WorkTime mapRow(ResultSet resultSet, int i) throws SQLException {

        WorkTime workTime = new WorkTime();
        workTime.setBookingId(resultSet.getLong("booking_id"));
        workTime.setStartBooking(resultSet.getTimestamp("start_booking").toLocalDateTime());
        workTime.setEndBooking(resultSet.getTimestamp("end_booking").toLocalDateTime());

        return workTime;

    }
}