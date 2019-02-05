package ua.ita.smartcarservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.car.CarOwner;

public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {

    CarOwner findByUserName(String userName);

    CarOwner getCarOwnerById(Long id);

}
