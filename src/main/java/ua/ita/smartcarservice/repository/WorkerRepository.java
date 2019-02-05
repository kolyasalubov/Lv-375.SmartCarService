package ua.ita.smartcarservice.repository;

import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import ua.ita.smartcarservice.entity.sto.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query("select w from Worker as w LEFT JOIN Skill as s on w.skill = s.skillId where s.name = :name and w.technicalService = :stoId")
    List<Worker> findAllWorkersBySkillAndSto(@Param("name") String name, @Param("stoId") Long stoId);


    List<Worker> findWorkersByTechnicalManager(TechnicalManager technicalManager);

}
