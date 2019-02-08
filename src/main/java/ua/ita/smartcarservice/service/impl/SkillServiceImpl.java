package ua.ita.smartcarservice.service.impl;

import ua.ita.smartcarservice.dto.stoDto.SkillDto;
import ua.ita.smartcarservice.entity.sto.Skill;
import ua.ita.smartcarservice.repository.SkillRepository;
import ua.ita.smartcarservice.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Skill service
 */
@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository repository;

    /*
    Method for getting all the Skills from DB
     */
    @Override
    public List<SkillDto> getAllSkills() {
        List<SkillDto> allSkill = new ArrayList<>();
        for (Skill skill : repository.findAll()) {
            allSkill.add(getSkillDto(skill));
        }
        return allSkill;
    }

    /*
    Method converts Skill entity to DTO
     */
    @Override
    public SkillDto getSkillDto(Skill skill) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getSkillId());
        skillDto.setName(skill.getName());
        skillDto.setFaultCode(skill.getFaultCode());
        skillDto.setRequiredTime(skill.getRequiredTime());
        return skillDto;
    }

    /*
    Method returns Skill entity by id
     */
    @Override
    public Skill getSkillById(Long id) {
        return repository.findById(id).get();
    }
}
