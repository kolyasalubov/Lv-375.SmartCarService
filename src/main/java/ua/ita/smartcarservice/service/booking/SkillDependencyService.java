package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.entity.booking.SkillDependency;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

import java.util.List;
import java.util.Map;

public interface SkillDependencyService {

    List<SkillDependency> findAll();

    int findRequiredTime(List<String> skillName);

    int findGraphSize();

    Map<String, SkillEntity> getDistinctSkillByName();

}
