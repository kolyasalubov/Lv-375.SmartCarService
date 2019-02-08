package ua.ita.smartcarservice.repository.sensors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.data.TirePressureEntity;
import ua.ita.smartcarservice.repository.sensors.factory.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TirePressureRepository extends SensorRepository<TirePressureEntity> {

    /* READ */

    @Override
    @Query("SELECT t FROM TirePressureEntity t WHERE t.car.id = :carId " +
            "AND DAY(t.date) = DAY(:date) AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)")
    List<TirePressureEntity> getAllByDay(@Param("date") LocalDateTime date,
                                         @Param("carId") long carId);

    @Override
    @Query("SELECT DAY(t.date), AVG(t.valueFrontLeft), AVG(t.valueFrontRight), AVG(t.valueBackLeft), AVG(t.valueBackRight) " +
            "FROM TirePressureEntity t " +
            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY DAY(t.date)")
    List getAvgByMonth(@Param("date") LocalDateTime date,
                       @Param("carId") long carId);

    @Override
    @Query("SELECT DAY(t.date), MAX(t.valueFrontLeft), MAX(t.valueFrontRight), MAX(t.valueBackLeft), MAX(t.valueBackRight) " +
            "FROM TirePressureEntity t " +
            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY DAY(t.date)")
    List getMaxByMonth(@Param("date") LocalDateTime date,
                       @Param("carId") long carId);

    @Override
    @Query("SELECT DAY(t.date), MIN(t.valueFrontLeft), MIN(t.valueFrontRight), MIN(t.valueBackLeft), MIN(t.valueBackRight) " +
            "FROM TirePressureEntity t " +
            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY DAY(t.date)")
    List getMinByMonth(@Param("date") LocalDateTime date,
                       @Param("carId") long carId);

    @Override
    @Query("SELECT MONTH(t.date), AVG(t.valueFrontLeft), AVG(t.valueFrontRight), AVG(t.valueBackLeft), AVG(t.valueBackRight) " +
            "FROM TirePressureEntity t " +
            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY MONTH(t.date)")
    List getAvgByYear(@Param("date") LocalDateTime date,
                      @Param("carId") long carId);

    @Override
    @Query("SELECT MONTH(t.date), MAX(t.valueFrontLeft), MAX(t.valueFrontRight), MAX(t.valueBackLeft), MAX(t.valueBackRight) " +
            "FROM TirePressureEntity t " +
            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY MONTH(t.date)")
    List getMaxByYear(@Param("date") LocalDateTime date,
                      @Param("carId") long carId);

    @Override
    @Query("SELECT MONTH(t.date), MIN(t.valueFrontLeft), MIN(t.valueFrontRight), MIN(t.valueBackLeft), MIN(t.valueBackRight) " +
            "FROM TirePressureEntity t " +
            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY MONTH(t.date)")
    List getMinByYear(@Param("date") LocalDateTime date,
                      @Param("carId") long carId);

}
