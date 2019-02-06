package ua.ita.smartcarservice.controller;

import ua.ita.smartcarservice.dto.stoDto.SkillDto;
import ua.ita.smartcarservice.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller (REST) that works only with skills
 * Opportunities: Get all the skills
 */
@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;


    /*
     * Method for getting all the skills
     */
    @GetMapping("/api/v1/skills")
    public List<SkillDto> getAllSkill(){
        return skillService.getAllSkills();
    }


}
