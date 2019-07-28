package ua.ita.smartcarservice.repository.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.files.CarImageEntity;

import java.util.List;

@Repository
public interface CarImageRepository extends JpaRepository<CarImageEntity, Long> {

    List<CarImageEntity> findByUsername(String username);

    CarImageEntity findByCarId(Car car);

}
