package ua.ita.smartcarservice.controller;

import ua.ita.smartcarservice.dto.stoDto.SkillDto;
import ua.ita.smartcarservice.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/skills")
    public List<SkillDto> getAllSkill(){
        return skillService.getAllSkills();
    }


}
