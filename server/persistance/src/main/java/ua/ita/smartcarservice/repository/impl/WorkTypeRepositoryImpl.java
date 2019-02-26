package ua.ita.smartcarservice.repository.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.repository.dataSource.SpringJdbcConfig;
import ua.ita.smartcarservice.repository.mapped.WorkTypeMapped;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component("wort_type_repository")
public class WorkTypeRepositoryImpl {

    public List <WorkType> getAllWorkTypeBySkillName(List <String> name) {

        Map namedParameters = Collections.singletonMap("skillName", name);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(new SpringJdbcConfig().mysqlDataSource());

        StringBuffer query = new StringBuffer();

        query.append("select * from work_type as w left join skills as s " +
                "on w.skill_id = s.skill_id where s.name in (:skillName);");



        return namedParameterJdbcTemplate.query(query.toString(), namedParameters, new WorkTypeMapped());

    }
}
