package ua.ita.smartcarservice.initialization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.repository.technicalservice.SkillRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(SkillRepository repository) {
        return args -> {
            //repository.deleteAll();
            List<SkillEntity> repositorySkills = repository.findAll();

            List<SkillEntity> populationList = new ArrayList<>();

            populationList.add(new SkillEntity("Service Technician", 2L));
            populationList.add(new SkillEntity("Diagnostic Technician", 3L));
            populationList.add(new SkillEntity("Transmission Technician", 4L));
            populationList.add(new SkillEntity("Brake Technician", 2L));
            populationList.add(new SkillEntity("Body Repair Technician", 5L));
            populationList.add(new SkillEntity("Vehicle Refinisher", 3L));
            populationList.add(new SkillEntity("Vehicle Inspector", 5L));

            for(SkillEntity eachSkill: populationList){
                if(!repositorySkills.contains(eachSkill)){
                    repository.save(eachSkill);
                }
            }
        };
    }

}
