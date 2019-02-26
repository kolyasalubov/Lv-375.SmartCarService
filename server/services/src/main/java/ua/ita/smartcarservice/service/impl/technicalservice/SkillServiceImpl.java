package ua.ita.smartcarservice.service.impl.technicalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.technicalservice.SkillDto;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.repository.technicalservice.SkillRepository;
import ua.ita.smartcarservice.service.technicalservice.SkillService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Skill service
 */
@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    /*
    Method for getting all the Skills from DB
     */
    @Override
    public List<SkillDto> getAllSkills() {
        List<SkillDto> allSkill = new ArrayList<>();
        for (SkillEntity skill : skillRepository.findAll()) {
            allSkill.add(getSkillDto(skill));
        }
        return allSkill;
    }

    @Override
    public Map<String, SkillEntity> findDistinctSkillByName(){
        Map<String, SkillEntity> requiredTimeByName = new HashMap<>();
        skillRepository.findAll().forEach(skillEntity -> requiredTimeByName.put(skillEntity.getName(), skillEntity));
        return requiredTimeByName;
    }

    /*
    Method converts Skill entity to DTO
     */
    @Override
    public SkillDto getSkillDto(SkillEntity skill) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getSkillId());
        skillDto.setName(skill.getName());
        return skillDto;
    }

    @Override
    public List<SkillDto> findSkillNameBySto(Long id){
        List<SkillDto> skillBySto = new ArrayList <>();

        skillRepository.findSkillNameBySto(id).forEach(skillEntity -> skillBySto.add(getSkillDto(skillEntity)));

        return skillBySto;
    }

    @Override
    public List<SkillDto> findSkillNameByCarId(Long carId){
        List<SkillDto> skillBySto = new ArrayList <>();

        skillRepository.findSkillNameByCarId(carId).forEach(skillEntity -> skillBySto.add(getSkillDto(skillEntity)));

        return skillBySto;
    }


    /*
    Method returns Skill entity by id
     */
    @Override
    public SkillEntity getSkillById(Long id) {
        return skillRepository.findById(id).get();
    }
}
