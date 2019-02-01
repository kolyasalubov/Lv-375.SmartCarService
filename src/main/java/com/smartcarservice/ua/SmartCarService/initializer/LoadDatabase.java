package com.smartcarservice.ua.SmartCarService.initializer;

import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import com.smartcarservice.ua.SmartCarService.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.lang.System.*;

@Configuration
@Slf4j
public class LoadDatabase {

//    @Bean
//    CommandLineRunner initDatabase(SkillRepository repository){
//        return args -> {
//            repository.deleteAll();
//            out.println("Preloading " + repository.save(new Skill("Service Technician")));
//            out.println("Preloading " + repository.save(new Skill("Diagnostic Technician")));
//            out.println("Preloading " + repository.save(new Skill("Transmission Technician")));
//            out.println("Preloading " + repository.save(new Skill("Brake Technician")));
//            out.println("Preloading " + repository.save(new Skill("Body Repair Technician")));
//            out.println("Preloading " + repository.save(new Skill("Vehicle Refinisher")));
//            out.println("Preloading " + repository.save(new Skill("Vehicle Inspector")));
//      };
//   }
}
