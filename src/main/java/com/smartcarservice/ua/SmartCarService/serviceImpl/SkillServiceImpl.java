package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.SkillDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import com.smartcarservice.ua.SmartCarService.repository.SkillRepository;
import com.smartcarservice.ua.SmartCarService.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository repository;

    @Override
    public List<SkillDto> getAllSkill() {
        List<SkillDto> allSkill = new ArrayList <>();
        for(Skill skill : repository.findAll()){
            allSkill.add(getSkillDto(skill));
        }
        return allSkill;

    }

    private SkillDto getSkillDto(Skill skill){
        SkillDto skillDto = new SkillDto();
        skillDto.setName(skill.getName());
        skillDto.setFaultCode(skill.getFaultCode());
        skillDto.setSpendTime(skill.getSpendTime());
        return skillDto;
    }
}
