package ua.ita.smartcarservice.initialization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.ita.smartcarservice.entity.alerts.FaultCode;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.repository.alerts.FaultCodeRepository;
import ua.ita.smartcarservice.repository.technicalservice.SkillRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(SkillRepository repository, FaultCodeRepository faultCodeRepository) {
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
            
            List<FaultCode> repositoryfaultCodes = faultCodeRepository.findAll();
            List<FaultCode> faultCodesToAdd = new ArrayList<>();
            Set<String> codes = new HashSet<>();
            
            faultCodesToAdd.add(new FaultCode("B1315", 
    								"Problem in Front passenger child seat recognition",
    								"warning", 
    								repository.getOne(1l)));
            faultCodesToAdd.add(new FaultCode("C1010",
				            		"Battery Voltage Low",
									"warning", 
									repository.getOne(2l)));
            faultCodesToAdd.add(new FaultCode("C1012",
				            		"Battery Voltage High",
									"warning", 
									repository.getOne(2l)));
            faultCodesToAdd.add(new FaultCode("C1512", 
				            		"Brakes Overheated",
									"warning", 
									repository.getOne(4l)));
            faultCodesToAdd.add(new FaultCode("P1791", 
				            		"Transmission Range Display Circ. Short to Ground",
									"warning", 
									repository.getOne(3l)));
            faultCodesToAdd.add(new FaultCode("B1476", 
				            		"Airbag malfunction indicator and warning lamp is defective",
									"warning", 
									repository.getOne(5l)));
            faultCodesToAdd.add(new FaultCode("P1296", 
				            		"Engine/Transmission Control Modules Versions do not match",
									"warning", 
									repository.getOne(3l)));
            faultCodesToAdd.add(new FaultCode("B1617", 
				            		"Left/Right license plate lamp is defective",
									"warning", 
									repository.getOne(5l)));
            faultCodesToAdd.add(new FaultCode("P1659", 
				            		"Brake Switch Circ. Error Message from Engine Contr.",
									"warning", 
									repository.getOne(4l)));
            faultCodesToAdd.add(new FaultCode("P1659", 
				            		"Brake Switch Circ. Error Message from Engine Contr.",
									"warning", 
									repository.getOne(6l)));
            
            for (FaultCode f : repositoryfaultCodes) {
				codes.add(f.getFaultCode());
            }
            for(FaultCode f: faultCodesToAdd){
                if(!codes.contains(f.getFaultCode())){
                	faultCodeRepository.save(f);
                }
            }
        };
    }

}
