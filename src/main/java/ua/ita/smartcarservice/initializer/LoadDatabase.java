package ua.ita.smartcarservice.initializer;

import ua.ita.smartcarservice.entity.sto.Skill;
import ua.ita.smartcarservice.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(SkillRepository repository) {
        return args -> {
            //repository.deleteAll();
            List<Skill> repositorySkills = repository.findAll();

            List<Skill> populationList = new ArrayList<>();

            populationList.add(new Skill("service Technician", 2L));
            populationList.add(new Skill("Diagnostic Technician", 3L));
            populationList.add(new Skill("Transmission Technician", 4L));
            populationList.add(new Skill("Brake Technician", 2L));
            populationList.add(new Skill("Body Repair Technician", 5L));
            populationList.add(new Skill("Vehicle Refinisher", 3L));
            populationList.add(new Skill("Vehicle Inspector", 5L));

            for(Skill eachSkill: populationList){
                if(!repositorySkills.contains(eachSkill)){
                    repository.save(eachSkill);
                }
            }
        };
    }

}
