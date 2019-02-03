package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.SkillDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import com.smartcarservice.ua.SmartCarService.repository.SkillRepository;
import com.smartcarservice.ua.SmartCarService.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private SkillDto getSkillDto(Skill skill){
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getSkillId());
        skillDto.setName(skill.getName());
        skillDto.setFaultCode(skill.getFaultCode());
        return skillDto;
    }

    @Override
    public Skill getSkillById(Long id) {
        return repository.findById(id).get();
    }
}
