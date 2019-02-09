package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.technicalservice.SkillDto;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

import java.util.List;

public interface SkillService {
    List<SkillDto> getAllSkills();
    SkillDto getSkillDto(SkillEntity skill);
    SkillEntity getSkillById(Long id);
    List<String> getSkillNameBySto(Long id);
}
