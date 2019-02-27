package ua.ita.smartcarservice.repository.mapped;

import org.springframework.jdbc.core.RowMapper;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkTypeMapped implements RowMapper<WorkType> {

    public WorkType mapRow(ResultSet resultSet, int i) throws SQLException {

        WorkType workType = new WorkType();
        workType.setWorkId(resultSet.getLong("work_id"));
        workType.setName(resultSet.getString("name"));
        workType.setCost(resultSet.getLong("cost"));
        workType.setRequiredTime(resultSet.getLong("required_time"));


        return workType;

    }
}
