package ua.ita.smartcarservice.repository.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.booking.WorkTime;
import ua.ita.smartcarservice.repository.dataSource.SpringJdbcConfig;
import ua.ita.smartcarservice.repository.mapped.WorkTimeMapped;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component("work_time_repository")
public class WorkTimeRepositoryImpl {

    public List<WorkTime> getAllBookingById(List<Long> id, LocalDateTime start, LocalDateTime end){

        Map namedParameters = Collections.singletonMap("workerId", id);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(new SpringJdbcConfig().mysqlDataSource());

        String query = "select * from schedule as s where worker_id in (:workerId) " +
                "and s.start_booking >= ('%s') " +
                "and s.end_booking <= ('%s');";
        String.format(query, getTimeValue(start.toString()), getTimeValue(end.toString()));


        return namedParameterJdbcTemplate.query(query, namedParameters, new WorkTimeMapped());
    }

    private String getTimeValue(String s){
        return s.substring(0, s.indexOf('T')) + " " + s.substring(s.indexOf('T') + 1);
    }
}
