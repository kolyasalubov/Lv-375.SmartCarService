package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.dto.StoDto.SkillDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;

import java.util.List;

public interface SkillService {
    List<SkillDto> getAllSkill();
}
