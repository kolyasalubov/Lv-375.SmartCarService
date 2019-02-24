package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {

    @Query("select max(w.workId) from WorkType as w")
    Long findMaxId();

}
