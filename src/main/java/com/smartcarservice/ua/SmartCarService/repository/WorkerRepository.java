package com.smartcarservice.ua.SmartCarService.repository;

import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    List<Worker> findBySkill(String skill);

    @Query("select w from Worker as w LEFT JOIN Skill as s on w.skill = s.skillId where s.name = :name")
    List<Worker> findAllWorkersBySkill(@Param("name") String name);
    List<Worker> findWorkersByTechnicalManager(TechnicalManager technicalManager);
}
