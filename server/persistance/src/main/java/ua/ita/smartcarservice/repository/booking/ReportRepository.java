package ua.ita.smartcarservice.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.entity.booking.WorkTime;


import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {


    @Query("SELECT new ReportEntity (r, r.car, r.technicalService) FROM ReportEntity r JOIN Car c ON r.car.id = c.id WHERE c.user.id = :id")
   // @Query("SELECT r FROM ReportEntity r LEFT JOIN FETCH r.car, r.technicalService")
    List<ReportEntity> findAllReportsByUserId(@Param("id") Long userId);


}
