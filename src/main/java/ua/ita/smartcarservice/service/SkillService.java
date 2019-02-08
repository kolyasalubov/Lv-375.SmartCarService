package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.stoDto.SkillDto;
import ua.ita.smartcarservice.entity.sto.Skill;

import java.util.List;

public interface SkillService {
    List<SkillDto> getAllSkills();
    SkillDto getSkillDto(Skill skill);
    Skill getSkillById(Long id);
    List<String> getSkillNameBySto(Long id);
}
