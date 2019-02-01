package com.smartcarservice.ua.SmartCarService.controller;

import com.smartcarservice.ua.SmartCarService.dto.StoDto.SkillDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import com.smartcarservice.ua.SmartCarService.repository.SkillRepository;
import com.smartcarservice.ua.SmartCarService.service.SkillService;
import com.smartcarservice.ua.SmartCarService.serviceImpl.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/skills")
    public List<SkillDto> all(){
        return skillService.getAllSkill();
    }
}
