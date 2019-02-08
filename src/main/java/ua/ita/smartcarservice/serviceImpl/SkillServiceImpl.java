package ua.ita.smartcarservice.serviceImpl;

import ua.ita.smartcarservice.dto.stoDto.SkillDto;
import ua.ita.smartcarservice.entity.sto.Skill;
import ua.ita.smartcarservice.repository.SkillRepository;
import ua.ita.smartcarservice.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository repository;

    @Override
    public List<SkillDto> getAllSkills() {
        List<SkillDto> allSkill = new ArrayList <>();
        for(Skill skill : repository.findAll()){
            allSkill.add(getSkillDto(skill));
        }
        return allSkill;

    }
    @Override
    public SkillDto getSkillDto(Skill skill){
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getSkillId());
        skillDto.setName(skill.getName());
        skillDto.setFaultCode(skill.getFaultCode());
        skillDto.setRequiredTime(skill.getRequiredTime());
        return skillDto;
    }

    @Override
    public Skill getSkillById(Long id) {
        return repository.findById(id).get();
    }
}
