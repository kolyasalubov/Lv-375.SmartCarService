package ua.ita.smartcarservice.controller.technicalservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.technicalservice.SkillDto;
import ua.ita.smartcarservice.service.SkillService;

import java.util.List;

/**
 * Controller (REST) that works only with skills
 * Opportunities: Get all the skills
 */
@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;
    
    @PostMapping("/skillbysto")
    public ResponseEntity<List<String>> getSkillByStoId(@RequestParam(value = "stoId", required = false) Long stoId){
        return new ResponseEntity <>(skillService.getSkillNameBySto(stoId), HttpStatus.OK);
    }

    /*
     * Method for getting all the skills
     */
    @GetMapping("/api/v1/skills")
    public ResponseEntity<List<SkillDto>> getAllSkill(){

        ResponseEntity<List<SkillDto>> responseEntity;

        try{
            responseEntity = new ResponseEntity<>(skillService.getAllSkills(), HttpStatus.OK);
        } catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }


}