package com.smartcarservice.ua.SmartCarService.initializer;

import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import com.smartcarservice.ua.SmartCarService.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.lang.System.*;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(SkillRepository repository) {
        return args -> {
            //repository.deleteAll();
            List<Skill> list = repository.findAll();

            if (!list.contains(new Skill("Service Technician"))) {
                repository.save(new Skill("Service Technician"));
            }
            if (!list.contains(new Skill("Diagnostic Technician"))) {
                repository.save(new Skill("Diagnostic Technician"));
            }
            if (!list.contains(new Skill("Transmission Technician"))) {
                repository.save(new Skill("Transmission Technician"));
            }
            if (!list.contains(new Skill("Brake Technician"))) {
                repository.save(new Skill("Brake Technician"));
            }
            if (!list.contains(new Skill("Body Repair Technician"))) {
                repository.save(new Skill("Body Repair Technician"));
            }
            if (!list.contains(new Skill("Vehicle Refinisher"))) {
                repository.save(new Skill("Vehicle Refinisher"));
            }
            if (!list.contains(new Skill("Vehicle Inspector"))) {
                repository.save(new Skill("Vehicle Inspector"));
            }
        };
    }

}
