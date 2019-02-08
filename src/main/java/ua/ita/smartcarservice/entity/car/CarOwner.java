package ua.ita.smartcarservice.entity.car;

import ua.ita.smartcarservice.entity.UserBaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "carowners")
public class CarOwner extends UserBaseEntity {


    @OneToMany(mappedBy = "carOwner")
    private Set<Car> cars;
}