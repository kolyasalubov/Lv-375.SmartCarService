package ua.ita.smartcarservice.repository;

import ua.ita.smartcarservice.entity.sto.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findAllByWorker_WorkerId(Long workerId);

    @Query("select s from Session as s left join Worker as w on s.worker = w.workerId where w.workerId = :workerId " +
            "and year(s.startSession) = year(:time) and month(s.startSession) = month(:time) and day(s.startSession) = day(:time)")
    List<Session> findTimeWhenWork(@Param("workerId") Long workerId,
                                   @Param("time") LocalDate time);

    @Query("select count(s) from Session as s where :starttime < s.endSession and :endtime > s.startSession")
    int selectNumberOfSessionWithDate(@Param("starttime") LocalDateTime starttime, @Param("endtime") LocalDateTime endtime);

}
