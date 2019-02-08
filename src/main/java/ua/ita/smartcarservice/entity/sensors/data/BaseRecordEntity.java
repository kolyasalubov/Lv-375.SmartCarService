package ua.ita.smartcarservice.entity.sensors.data;


import ua.ita.smartcarservice.entity.car.Car;

import ua.ita.smartcarservice.entity.sensors.DateEntity;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class BaseRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Car car;

//    TODO fix foreign key & add to car
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carEntity")
//    private List<RecordEntity> records;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "date_id", nullable = false)
    private DateEntity dateEntity;

}
